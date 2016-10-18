package se.ivankrizsan.restexample.restadapter;


import org.springframework.transaction.annotation.Transactional;
import se.ivankrizsan.restexample.domain.LongIdEntity;
import se.ivankrizsan.restexample.services.AbstractServiceBase;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Abstract base class for REST resources exposing operations on an entity type.
 * All operations will return HTTP status 500 with a plain text body containing an
 * error message if an error occurred during request processing.
 *
 * @param <E> Entity type.
 * @author Ivan Krizsan
 */
@Transactional
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON})
public abstract class RestResourceBase<E extends LongIdEntity> {
    /* Constant(s): */

    /* Instance variable(s): */
    protected AbstractServiceBase<E> mService;

    /**
     * Retrieves all entities.
     *
     * @param inAsyncResponse Asynchronous response object.
     */
    @GET
    public void getAll(@Suspended final AsyncResponse inAsyncResponse) {

        mService.findAll().subscribe(
            inResult -> inAsyncResponse.resume(Response.ok(inResult).build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity("An error occurred retrieving all entities: " + inError.getMessage()).
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()));
    }

    /**
     * Deletes the entity with supplied id.
     *
     * @param inAsyncResponse Asynchronous response object.
     * @param inEntityId Id of entity to delete.
     */
    @DELETE
    @Path("{id}")
    public void deleteEntityById(
        @Suspended final AsyncResponse inAsyncResponse, @PathParam("id") @NotNull final Long inEntityId) {
        mService.delete(inEntityId).subscribe(
            inResult -> inAsyncResponse.resume(Response.ok().build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity("An error occurred deleting entity with id " + inEntityId).
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()),
            () -> inAsyncResponse.resume(Response.ok().build()));
    }

    /**
     * Deletes all entities.
     * Will return HTTP status 500 if error occurred during request processing.
     *
     * @param inAsyncResponse Asynchronous response object.
     */
    @DELETE
    public void deleteAllEntities(@Suspended final AsyncResponse inAsyncResponse) {
        mService.deleteAll().subscribe(
            inResult -> inAsyncResponse.resume(Response.ok().build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity("An error occurred deleting all entities.").
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()),
            () -> inAsyncResponse.resume(Response.ok().build()));
    }

    /**
     * Retrieves entity with supplied id.
     *
     * @param inEntityId Id of entity to retrieve.
     * @param inAsyncResponse Asynchronous response object.
     */
    @GET
    @Path("{id}")
    public void getEntityById(@PathParam("id") Long inEntityId, @Suspended final AsyncResponse inAsyncResponse) {
        mService.find(inEntityId).subscribe(
            inResult -> inAsyncResponse.resume(Response.ok(inResult).build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity(inError.getMessage()).
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()));
    }

    /**
     * Updates the entity with supplied id by overwriting it with the supplied entity.
     *
     * @param inAsyncResponse Asynchronous response object.
     * @param inEntity Entity data to write.
     * @param inEntityId Id of entity to update.
     */
    @PUT
    @Path("{id}")
    public void updateEntity(@Suspended final AsyncResponse inAsyncResponse, final E inEntity,
        @PathParam("id") @NotNull final Long inEntityId) {
        inEntity.setId(inEntityId);
        mService.save(inEntity).subscribe(
            inResult -> inAsyncResponse.resume(Response.ok(inResult).build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity("An error occurred updating entity with id " + inEntityId + ": " + inError.getMessage()).
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()));
    }

    /**
     * Creates a new entity using the supplied entity data.
     *
     * @param inAsyncResponse Asynchronous response object.
     * @param inEntity Entity data to use when creating new entity.
     */
    @POST
    public void createEntity(
        @Suspended final AsyncResponse inAsyncResponse, final E inEntity) {
        if (inEntity.getId() != null) {
            final Response response = Response.status(400).entity("Id must not be set on new entity").build();
            inAsyncResponse.resume(response);
        }

        mService.save(inEntity).subscribe(
            inResult -> inAsyncResponse.resume(Response.ok(inResult).build()),
            inError -> inAsyncResponse.resume(
                Response.
                    status(500).
                    entity("An error occurred creating a new entity: " + inError.getMessage()).
                    type(MediaType.TEXT_PLAIN_TYPE).
                    build()));
    }

    public AbstractServiceBase<E> getService() {
        return mService;
    }

    public void setService(final AbstractServiceBase<E> inService) {
        mService = inService;
    }
}
