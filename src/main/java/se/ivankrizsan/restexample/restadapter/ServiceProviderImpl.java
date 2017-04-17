package se.ivankrizsan.restexample.restadapter;

import org.springframework.stereotype.Service;

import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.domain.Drawing;
import se.ivankrizsan.restexample.domain.Rectangle;
import se.ivankrizsan.restexample.services.AbstractServiceBaseRxJava;

/**
 * Created by houssoli on 17/04/17.
 */
@Service
public class ServiceProviderImpl implements ServiceProvider {

    private final AbstractServiceBaseRxJava<Circle> circleService;
    private final AbstractServiceBaseRxJava<Rectangle> rectangleService;
    private final AbstractServiceBaseRxJava<Drawing> drawingService;

    public ServiceProviderImpl(AbstractServiceBaseRxJava<Circle> circleService, AbstractServiceBaseRxJava<Rectangle> rectangleService, AbstractServiceBaseRxJava<Drawing> drawingService) {
        this.circleService = circleService;
        this.rectangleService = rectangleService;
        this.drawingService = drawingService;
    }

    @Override
    public AbstractServiceBaseRxJava<Circle> getCircleService() {
        return circleService;
    }

    @Override
    public AbstractServiceBaseRxJava<Rectangle> getRectangleService() {
        return rectangleService;
    }

    @Override
    public AbstractServiceBaseRxJava<Drawing> getDrawingService() {
        return drawingService;
    }
}
