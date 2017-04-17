package fr.soat.houssoli.graphqlexample.schema.execution;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import io.reactivex.Observable;

public class RxExecutionResult implements ExecutionResult {

    private static final Logger logger = LoggerFactory.getLogger(RxExecutionResult.class);

    private Observable<?> dataObservable;

    private Observable<List<GraphQLError>> errorsObservable;

    public RxExecutionResult(Observable<?> data, Observable<List<GraphQLError>> errors) {
        dataObservable = data;
        errorsObservable = errors;
    }

    public Observable<?> getDataObservable() {
        return dataObservable;
    }

    public Observable<List<GraphQLError>> getErrorsObservable() {
        return errorsObservable;
    }

    @Override
    public Object getData() {
        logger.warn("getData() called instead of getDataObservable(), blocking (likely a bug)");
        return dataObservable.blockingFirst();
    }

    @Override
    public List<GraphQLError> getErrors() {
        logger.warn("getErrors() called instead of getErrorsObservable(), blocking (likely a bug)");
        return errorsObservable.blockingFirst();
    }
}
