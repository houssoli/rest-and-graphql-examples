package fr.soat.houssoli.graphqlexample.schema.objecttype;

import graphql.annotations.GraphQLDescription;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLNonNull;

/**
 * Created by houssoli on 09/04/17.
 */
public class CircleObjectType extends ShapeObjectType {

    /* Constant(s): */
    public final static int DEFAULT_RADIUS = 10;

    /* Instance variable(s): */
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Radius of circle shape")
    protected int radius = DEFAULT_RADIUS;

    /**
     * Default constructor.
     */
    public CircleObjectType() {
    }

    /**
     * Creates a circle with the supplied radius.
     *
     * @param inRadius Radius of the new circle.
     */
    public CircleObjectType(final int inRadius) {
        radius = inRadius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(final int inRadius) {
        radius = inRadius;
    }
}
