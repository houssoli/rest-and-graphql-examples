package fr.soat.houssoli.graphqlexample.schema.objecttype;

import org.springframework.beans.factory.annotation.Autowired;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLDescription;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLNonNull;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;

import fr.soat.houssoli.graphqlexample.schema.GraphqlExampleSchema;

/**
 * Created by houssoli on 09/04/17.
 */
@GraphQLObject("Root")
public class RootObjectType {

    @GraphQLNonNull
    @GraphQLField("version")
    @GraphQLDescription("Root query version number")
    public static final String VERSION = "0.0.1";

    @Autowired
    @GraphQLIgnore
    private GraphqlExampleSchema todoSchema;
}
