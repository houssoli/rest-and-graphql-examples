package fr.soat.houssoli.graphqlexample.schema;

import static fr.soat.houssoli.graphqlexample.schema.ExampleSchema.QUERY;

import java.util.List;

import org.springframework.stereotype.Component;

import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;
import se.ivankrizsan.restexample.domain.Circle;


/**
 * Created by houssoli on 09/04/17.
 */
@Component
@GraphQLName(QUERY)
public class QuerySchema {

    protected CircleSchema circleSchema;

    // TODO : implement GraphQLField "circles" to get all circles
    @GraphQLField
    public List<Circle> circles(final DataFetchingEnvironment env) {
        return circleSchema.allCircles();
    }
}
