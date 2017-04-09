package fr.soat.houssoli.graphqlexample.schema.objecttype;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import java.util.Objects;
import java.util.StringJoiner;

import com.oembedler.moon.graphql.engine.relay.RelayNode;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLDescription;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLID;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLNonNull;

/**
 * Created by houssoli on 09/04/17.
 */
public class BaseObjectType {
    @GraphQLIgnore
    protected Long id;

    @GraphQLID("id")
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
