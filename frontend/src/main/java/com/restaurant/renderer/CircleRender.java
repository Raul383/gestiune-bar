package com.restaurant.renderer;

import com.restaurant.model.Circle;
import com.restaurant.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleRender implements ShapeRenderer<Circle> {

    @Override
    public void draw(
            GraphicsContext gc,
            Circle circle,
            RestaurantTransform transform) {

        Point center = transform.metersToPixels(
            circle.getX(),
            circle.getY()
        );

        double radius = transform.metersToPixelsDistance(
            circle.getRadius()
        );

        gc.strokeOval(
            center.getX() - radius,
            center.getY() - radius,
            radius * 2,
            radius * 2
        );
    }
}