package fr.soat.houssoli.graphqlexample.schema.fetcher;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.domain.Point;
import se.ivankrizsan.restexample.restadapter.GraphqlResource;
import se.ivankrizsan.restexample.restadapter.ServiceProvider;
import se.ivankrizsan.restexample.services.AbstractServiceBaseRxJava;

/**
 * Created by houssoli on 09/04/17.
 */
@Component
public class CircleDataFetcher implements DataFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(CircleDataFetcher.class);

    public CircleDataFetcher() {
        super();
    }

    protected List<Circle> allCircles(DataFetchingEnvironment env) {
        final Circle circle = new Circle(5);
        circle.setColour("Green");
        circle.setPosition(new Point(15, 20));
        circle.setId(7L);

        return Collections.singletonList(circle);
    }

    @Override
    public Object get(DataFetchingEnvironment env) {
        LOG.debug("dataFetchingEnvironment => {} | {}", env, ToStringBuilder.reflectionToString(env));
        final GraphqlResource source = (GraphqlResource) env.getSource();
        final ServiceProvider serviceProvider = (ServiceProvider) source.getServiceProvider();
        final AbstractServiceBaseRxJava<Circle> circleService = serviceProvider.getCircleService();

        return circleService.findAll();
        //return allCircles(env);
    }
}
