package fr.soat.houssoli.graphqlexample.schema;

import static fr.soat.houssoli.graphqlexample.schema.ExampleSchema.MUTATION;

import org.springframework.stereotype.Component;

import graphql.annotations.GraphQLName;

/**
 * Created by houssoli on 09/04/17.
 */
@Component
@GraphQLName(MUTATION)
public class MutationSchema {

    protected CircleSchema circleSchema;
}
