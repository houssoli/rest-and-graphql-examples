package fr.soat.houssoli.graphqlexample.schema;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLSchema;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLSchemaQuery;

import fr.soat.houssoli.graphqlexample.schema.objecttype.RootObjectType;

// TODO : implement GraphQL schema
/**
 * Created by houssoli on 09/04/17.
 */
@GraphQLSchema
public class GraphqlExampleSchema {

    @GraphQLSchemaQuery
    private RootObjectType root;

}
