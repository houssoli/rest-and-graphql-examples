package fr.soat.houssoli.graphqlexample.schema.objecttype;

import graphql.annotations.GraphQLDescription;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLNonNull;

/**
 * Created by houssoli on 09/04/17.
 */
public abstract class ShapeObjectType extends BaseObjectType {

    /* Instance variable(s): */

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Colour of the shape")
    protected String colour;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Position of the shape")
    protected PointObjectType position;

    public String getColour() {
        return colour;
    }

    public void setColour(final String inColour) {
        colour = inColour;
    }

    public PointObjectType getPosition() {
        return position;
    }

    public void setPosition(final PointObjectType inPosition) {
        position = inPosition;
    }
}
