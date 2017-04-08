package fr.soat.houssoli.graphqlexample.schema;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.restadapter.RestResourceBaseRxJava;
import se.ivankrizsan.restexample.services.CircleService;

// TODO : implement GraphQL schema
/**
 * REST resource exposing operations on circles.
 *
 * @author Ivan Krizsan
 */
@Component
@Path(GraphqlSchemaCircle.PATH)
public class GraphqlSchemaCircle extends RestResourceBaseRxJava<Circle> {
    /* Constant(s): */
    public final static String PATH = "/graphql/circle";

    /**
     * Creates a REST resource using the supplied service to manipulate entities.
     *
     * @param inService Service used to manipulate entities.
     */
    public GraphqlSchemaCircle(final CircleService inService) {
        setService(inService);
    }

    @Override
    protected Circle[] entityListToArray(final List<Circle> inEntityList) {
        return inEntityList.toArray(new Circle[inEntityList.size()]);
    }
}
