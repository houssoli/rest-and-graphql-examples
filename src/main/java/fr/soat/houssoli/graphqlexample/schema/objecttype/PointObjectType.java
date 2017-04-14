package fr.soat.houssoli.graphqlexample.schema.objecttype;

import graphql.annotations.GraphQLDescription;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLNonNull;
import graphql.annotations.GraphQLType;

/**
 * Created by houssoli on 09/04/17.
 */
@GraphQLType
public class PointObjectType {
    /**
     * The X coordinate of this <code>PointObjectType</code>.
     * If no X coordinate is set it will default to 0.
     */
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("X coordinate of this Point")
    private int x;

    /**
     * The Y coordinate of this <code>PointObjectType</code>.
     * If no Y coordinate is set it will default to 0.
     */
    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("Y coordinate of this Point")
    private int y;

    /**
     * Constructs and initializes a PointObjectType at the origin
     * (0,&nbsp;0) of the coordinate space.
     */
    public PointObjectType() {
        this(0, 0);
    }

    /**
     * Constructs and initializes a PointObjectType with the same location as
     * the specified <code>PointObjectType</code> object.
     *
     * @param p a PointObjectType
     */
    public PointObjectType(PointObjectType p) {
        this(p.x, p.y);
    }

    /**
     * Constructs and initializes a PointObjectType at the specified
     * {@code (x,y)} location in the coordinate space.
     *
     * @param x the X coordinate of the newly constructed <code>PointObjectType</code>
     * @param y the Y coordinate of the newly constructed <code>PointObjectType</code>
     */
    public PointObjectType(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
