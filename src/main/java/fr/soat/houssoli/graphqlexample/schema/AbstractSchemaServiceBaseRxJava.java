package fr.soat.houssoli.graphqlexample.schema;

import java.util.List;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.ivankrizsan.restexample.domain.LongIdEntity;
import se.ivankrizsan.restexample.services.AbstractServiceBaseRxJava;

/**
 * Created by houssoli on 09/04/17.
 */
abstract class AbstractSchemaServiceBaseRxJava<E extends LongIdEntity> {
    protected AbstractServiceBaseRxJava<E> mService;

    public void setService(AbstractServiceBaseRxJava<E> service) {
        this.mService = service;
    }

    /**
     * Creates an array containing the entities in the supplied list.
     *
     * @param inEntityList List of entities.
     * @return Array containing the entities from the list.
     */
    protected abstract E[] entityListToArray(final List<E> inEntityList);

    /**
     * Retrieves all entities.
     *
     * @param inAsyncResponse Asynchronous response object.
     */
    public void getAll(@Suspended final AsyncResponse inAsyncResponse) {

        mService.findAll().subscribe(
                inResult -> inAsyncResponse.resume(Response.ok(entityListToArray(inResult)).build()),
                inError -> inAsyncResponse.resume(
                        Response.
                                status(500).
                                entity("An error occurred retrieving all entities: " + inError.getMessage()).
                                type(MediaType.TEXT_PLAIN_TYPE).
                                build()));
    }

}
