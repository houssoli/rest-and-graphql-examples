package fr.soat.houssoli.graphqlexample.schema.execution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.ExecutionResult;
import graphql.execution.ExecutionContext;
import graphql.execution.ExecutionStrategy;
import graphql.language.Field;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import io.reactivex.Observable;
import io.reactivex.Single;


public class RxExecutionStrategy extends ExecutionStrategy {

    private final static Logger logger = LoggerFactory.getLogger(RxExecutionStrategy.class);

    @Override
    public ExecutionResult execute(ExecutionContext executionContext, GraphQLObjectType parentType, Object source, Map<String, List<Field>> fields) {

        List<Observable<Pair<String, ?>>> observables = new ArrayList<>();
        for (String fieldName : fields.keySet()) {
            final List<Field> fieldList = fields.get(fieldName);

            ExecutionResult executionResult = resolveField(executionContext, parentType, source, fieldList);

            if (executionResult instanceof RxExecutionResult) {
                RxExecutionResult rxResult = (RxExecutionResult) executionResult;
                Observable<?> unwrapped = rxResult.getDataObservable().flatMap(potentialResult -> {
                    if (potentialResult instanceof RxExecutionResult) {
                        return ((RxExecutionResult) potentialResult).getDataObservable();
                    }

                    if (potentialResult instanceof ExecutionResult) {
                        return Observable.just(((ExecutionResult) potentialResult).getData());
                    }

                    return Observable.just(potentialResult);
                });

                observables.add(Observable.zip(Observable.just(fieldName), unwrapped, Pair::of));
            } else {
                observables.add(Observable.just(Pair.of(fieldName, executionResult != null ? executionResult.getData() : null)));
            }
        }

        final Single<Map<String, Object>> mapSingle = Observable.merge(observables)
                .toMap(Pair::getLeft, Pair::getRight);

        final Observable<Map<String, Object>> result = mapSingle.toObservable();

        return new RxExecutionResult(result, Observable.just(executionContext.getErrors()));
    }

    @Override
    protected ExecutionResult completeValue(ExecutionContext executionContext, GraphQLType fieldType, List<Field> fields, Object result) {
        if (result instanceof Observable) {
            return new RxExecutionResult(((Observable<?>) result).map(r -> super.completeValue(executionContext, fieldType, fields, r)), null);
        }
        return super.completeValue(executionContext, fieldType, fields, result);
    }

    @Override
    protected ExecutionResult completeValueForList(ExecutionContext executionContext, GraphQLList fieldType, List<Field> fields, Iterable<Object> result) {
        final List<Object> resultList = StreamSupport.stream(result.spliterator(), false).collect(
                Collectors.<Object>toList());

        final ListTuple[] ListTuplesArray = IntStream.range(0, resultList.size())
                .mapToObj(idx -> new ListTuple(idx, resultList.get(idx)))
                .toArray(ListTuple[]::new);

        final Observable<?> resultObservable = Observable.fromArray(ListTuplesArray)
                .flatMap(tuple -> {
                    ExecutionResult executionResult = completeValue(executionContext, fieldType.getWrappedType(), fields, tuple.result);

                    if (executionResult instanceof RxExecutionResult) {
                        return Observable.zip(Observable.just(tuple.index), ((RxExecutionResult) executionResult).getDataObservable(), ListTuple::new);
                    }
                    return Observable.just(new ListTuple(tuple.index, executionResult.getData()));
                })
                .toList()
                .toObservable()
                .map(listTuples -> {
                    return listTuples.stream()
                            .sorted(Comparator.comparingInt(x -> x.index))
                            .map(x -> x.result)
                            .collect(Collectors.toList());
                });

        return new RxExecutionResult(resultObservable, null);
    }

    private class ListTuple {
        public int index;
        public Object result;

        public ListTuple(int index, Object result) {
            this.index = index;
            this.result = result;
        }
    }
}
