package fr.soat.houssoli.graphqlexample.schema.objecttype;

import java.util.Objects;
import java.util.StringJoiner;

import graphql.annotations.GraphQLDescription;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLNonNull;
import graphql.annotations.GraphQLType;

/**
 * Created by houssoli on 09/04/17.
 */
@GraphQLType
public class BaseObjectType {
    protected Long id;

    @GraphQLField
    @GraphQLNonNull
    @GraphQLDescription("object unique identifier")
    public Long getId(Long id) {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseObjectType that = (BaseObjectType) o;

        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("id = " + id)
                .toString();
    }
}
