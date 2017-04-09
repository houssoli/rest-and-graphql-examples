package fr.soat.houssoli.graphqlexample.schema;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.annotations.GraphQLField;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.services.CircleService;

// TODO : implement GraphQL schema

/**
 * GraphQL schema spare parts on circles
 * <p>
 * Created by houssoli on 09/04/17.
 */
@Component
public class CircleSchema extends AbstractSchemaServiceBaseRxJava<Circle> {
    //TODO : GraphQLFieldDefinition Circle builder impl
    /**
     * Constructor using the supplied service to manipulate entities.
     *
     * @param inService Service used to manipulate entities.
     */
    public CircleSchema(final CircleService inService) {
        setService(inService);
    }

    @Override
    protected Circle[] entityListToArray(List<Circle> inEntityList) {
        return new Circle[0];
    }

    @GraphQLField
    public List<Circle> allCircles() {
        return Collections.emptyList();
    }
}
