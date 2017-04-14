package fr.soat.houssoli.graphqlexample.schema.fetcher;

import java.awt.Point;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.soat.houssoli.graphqlexample.schema.AbstractGraphQLRessourceBaseRxJava;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.services.CircleService;

/**
 * Created by houssoli on 09/04/17.
 */
@Component
public class CircleDataFetcher extends AbstractGraphQLRessourceBaseRxJava<Circle> implements DataFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(CircleDataFetcher.class);
    /* Constant(s): */

    /* Instance variable(s): */
    protected CircleService mService;

    public CircleDataFetcher() {
        super();
        setService(mService);
    }

    /**
     * Constructor using the supplied service to manipulate entities.
     *
     * @param inService Service used to manipulate entities.
     */
    public CircleDataFetcher(final CircleService inService) {
        setService(inService);
    }

    @Override
    protected Circle[] entityListToArray(List<Circle> inEntityList) {
        return new Circle[0];
    }

    protected List<Circle> allCircles(DataFetchingEnvironment env) {
        final Circle circle = new Circle(5);
        circle.setColour("Green");
        circle.setPosition(new Point(15, 20));
        circle.setId(7L);

        return Collections.singletonList(circle);
    }

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        LOG.debug("dataFetchingEnvironment => {} | source => {}", dataFetchingEnvironment, dataFetchingEnvironment.getSource());
        return allCircles(dataFetchingEnvironment);
    }
}
