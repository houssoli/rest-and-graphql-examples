package fr.soat.houssoli.graphqlexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.soat.houssoli.graphqlexample.schema.GraphqlExampleSchema;

// TODO : implement GraphQL schema
/**
 * Created by houssoli on 09/04/17.
 */
@RestController
@Component
public class GraphqlResource {
    private static final Logger LOG = LoggerFactory.getLogger(GraphqlResource.class);

    @RequestMapping(method = RequestMethod.POST, value = "//TODO graphql")
    public Object singleService(@RequestBody(required = false) String query) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException {
        LOG.debug("query : " + query);

        // Prepare
/*        final GraphqlExampleSchema schema = new GraphqlExampleSchema(null);
        final graphql.GraphQL graphql = new graphql.Graph(schema.getSchema());*/

        // Execute
/*        final graphql.ExecutionResult executionResult = graphql.execute(query);*/
        final Object resultData = null;
        final Object resultErrors = null;
        LOG.debug("resultData  :" + resultData);
        LOG.debug("resultErrors  :" + resultErrors);
        return null;
    }
}
