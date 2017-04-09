package fr.soat.houssoli.graphqlexample.schema.fetcher;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created by houssoli on 09/04/17.
 */
@Component
public class CircleDataFetcher implements DataFetcher {

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        return null;
    }
}
