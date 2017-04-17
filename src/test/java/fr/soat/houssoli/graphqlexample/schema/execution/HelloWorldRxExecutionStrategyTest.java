package fr.soat.houssoli.graphqlexample.schema.execution;


import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class HelloWorldRxExecutionStrategyTest {

    public static void main(String[] args) {
        GraphQLObjectType queryType = newObject()
                .name("helloWorldQuery")
                .field(newFieldDefinition()
                        .type(GraphQLString)
                        .name("hello")
                        .staticValue(Observable.just("world"))
                        .build())
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        Observable<?> result = ((RxExecutionResult) new GraphQL(schema, new RxExecutionStrategy()).execute("{hello}")).getDataObservable();

        result.subscribe(System.out::println);
    }

    @Test
    public void helloWorldTest() {
        GraphQLObjectType queryType = newObject()
                .name("helloWorldQuery")
                .field(newFieldDefinition()
                        .type(GraphQLString)
                        .name("hello")
                        .staticValue(Observable.just("world"))
                        .build())
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        RxExecutionResult executionResult = (RxExecutionResult) new GraphQL(schema, new RxExecutionStrategy()).execute("{hello}");

        Observable<Map<String, Object>> result = (Observable<Map<String, Object>>) executionResult.getDataObservable();

        final TestObserver<Map<String, Object>> testObserver = new TestObserver<>();

        result.subscribe(testObserver);

        testObserver.awaitTerminalEvent();

        testObserver.assertNoErrors();

        Map<String, Object> response = testObserver.values().get(0);

        assertEquals("world", response.get("hello"));
    }

    //TODO queryHelloWorldTest
    @Test
    public void queryHelloWorldTest() {
        GraphQLObjectType queryType = newObject()
                .name("helloWorldQuery")
                .field(newFieldDefinition()
                        .type(GraphQLString)
                        .name("hello")
                        .staticValue(Observable.just("world"))
                        .build())
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        RxExecutionResult executionResult = (RxExecutionResult) new GraphQL(schema, new RxExecutionStrategy()).execute("{query {hello}}");

        Observable<Map<String, Object>> result = (Observable<Map<String, Object>>) executionResult.getDataObservable();

        final TestObserver<Map<String, Object>> testObserver = new TestObserver<>();

        result.subscribe(testObserver);

        testObserver.awaitTerminalEvent();

        testObserver.assertNoErrors();

        Map<String, Object> response = testObserver.values().get(0);

        assertEquals("world", response.get("hello"));
    }
}
