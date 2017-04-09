package fr.soat.houssoli.graphqlexample.schema;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;

import se.ivankrizsan.restexample.domain.Circle;

// TODO : implement GraphQL schema

/**
 * GraphQL schema spare parts on circles
 * <p>
 * Created by houssoli on 09/04/17.
 */
@Component
@GraphQLObject("circle")
public class GraphqlSchemaCircle {
    //TODO : GraphQLFieldDefinition Circle builder impl

    //TODO : DataFetcherCircle impl
    @Autowired
    @GraphQLIgnore
    protected GraphqlExampleSchema schema;

    @GraphQLField
    public List<Circle> allCircles() {

        return Collections.emptyList();
    }
}
