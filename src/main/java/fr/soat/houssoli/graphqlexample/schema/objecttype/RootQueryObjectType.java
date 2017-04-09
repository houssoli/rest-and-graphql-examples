package fr.soat.houssoli.graphqlexample.schema.objecttype;

import org.springframework.beans.factory.annotation.Autowired;

import fr.soat.houssoli.graphqlexample.schema.ExampleSchema;
import graphql.annotations.GraphQLDescription;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLNonNull;

/**
 * Created by houssoli on 09/04/17.
 */
public class RootQueryObjectType {

    @GraphQLNonNull
    @GraphQLField
    @GraphQLDescription("Root query version number")
    public static final String version = "0.0.1";

    @Autowired
    private ExampleSchema schema;
}
