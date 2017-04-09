package fr.soat.houssoli.graphqlexample.schema;

import static graphql.schema.GraphQLSchema.newSchema;

import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLSchema;

// TODO : implement GraphQL schema

/**
 * Created by houssoli on 09/04/17.
 */
public class ExampleSchema {
    public static final String QUERY = "query";
    public static final String MUTATION = "mutation";

    private final GraphQLSchema schema;

    public ExampleSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        schema = newSchema()
                .query(GraphQLAnnotations.object(QuerySchema.class))
                .mutation(GraphQLAnnotations.object(MutationSchema.class))
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
