package fr.soat.houssoli.graphqlexample.schema;

import static fr.soat.houssoli.graphqlexample.schema.ExampleSchema.QUERY;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.soat.houssoli.graphqlexample.schema.fetcher.CircleDataFetcher;
import fr.soat.houssoli.graphqlexample.schema.objecttype.CircleObjectType;
import graphql.annotations.GraphQLDataFetcher;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.schema.DataFetchingEnvironment;


/**
 * Created by houssoli on 09/04/17.
 */
@Component
@GraphQLName(QUERY)
public class QuerySchema {
    private static final Logger LOG = LoggerFactory.getLogger(QuerySchema.class);

    protected CircleSchema circleSchema;

    // TODO : implement GraphQLField "circles" to get all circles
    @GraphQLField
    @GraphQLDataFetcher(CircleDataFetcher.class)
    public List<CircleObjectType> circles(final DataFetchingEnvironment env) {
        LOG.debug("DataFetchingEnvironment env => {}", env);
        return null;//circleSchema.allCircles(env);
    }
}
