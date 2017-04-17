package se.ivankrizsan.restexample.restadapter;

import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.domain.Drawing;
import se.ivankrizsan.restexample.domain.Rectangle;
import se.ivankrizsan.restexample.services.AbstractServiceBaseRxJava;

/**
 * Created by houssoli on 16/04/17.
 */
public interface ServiceProvider {

    AbstractServiceBaseRxJava<Circle> getCircleService();

    AbstractServiceBaseRxJava<Rectangle> getRectangleService();

    AbstractServiceBaseRxJava<Drawing> getDrawingService();
}
