package se.ivankrizsan.restexample.restadapter;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.soat.houssoli.graphqlexample.schema.ExampleSchema;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.reactivex.Observable;

// TODO : implement GraphQL schema

/**
 * REST resource exposing operations for whole the GraphQL schema.
 * <p>
 * Created by houssoli on 09/04/17.
 */
@Component
@Path(GraphqlResource.PATH)
public class GraphqlResource {
    private static final Logger LOG = LoggerFactory.getLogger(GraphqlResource.class);
    public static final String PATH = "graphql";
    private final GraphQLSchema schema;
    private final GraphQL graphql;

    public GraphqlResource() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        // Prepare ExampleSchema
        schema = new ExampleSchema().getSchema();
        graphql = new GraphQL(schema);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Consumes({MediaType.APPLICATION_JSON})
    public Object singleService(@Suspended final AsyncResponse inAsyncResponse, String query) {
        LOG.debug("HOUSSOU GraphQL query => " + query);
        return Observable.create(inSource -> {
            try {
                // TODO : remove Hello world
                if ("".equals("x")) {
                    final HashMap<String, String> map = new HashMap<>();
                    map.put("data", "Hello world");
                }

                // Execute
                final ExecutionResult executionResult = graphql.execute(query);

                final Object resultData = executionResult.getData();
                final Object resultErrors = executionResult.getErrors();
                LOG.debug("resultData  :" + resultData);
                LOG.debug("resultErrors  :" + resultErrors);

                final Object next = resultData == null ? resultErrors : resultData;
                inSource.onNext(next);
                inSource.onComplete();
            } catch (final Exception theException) {
                inSource.onError(theException);
            }
        }).subscribe(
                inResult -> inAsyncResponse.resume(Response.ok(inResult).build()),
                inError -> inAsyncResponse.resume(
                        Response.
                                status(500).
                                entity("An error occurred processing query => " + query).
                                type(MediaType.TEXT_PLAIN_TYPE).
                                build()));
    }
}
