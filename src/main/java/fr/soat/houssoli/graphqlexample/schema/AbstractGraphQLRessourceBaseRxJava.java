package fr.soat.houssoli.graphqlexample.schema;

import java.util.List;

import io.reactivex.Observable;
import se.ivankrizsan.restexample.domain.LongIdEntity;
import se.ivankrizsan.restexample.services.AbstractServiceBaseRxJava;

/**
 * Created by houssoli on 09/04/17.
 */
public abstract class AbstractGraphQLRessourceBaseRxJava<E extends LongIdEntity> {
    /* Constant(s): */

    /* Instance variable(s): */
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
     */
    public Observable<List<E>> getAll() {
        return mService.findAll();
    }

}
