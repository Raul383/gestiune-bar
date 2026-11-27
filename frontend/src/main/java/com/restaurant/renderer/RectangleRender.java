package com.restaurant.renderer;

import com.restaurant.model.Point;
import com.restaurant.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleRender implements ShapeRenderer<Rectangle> {

@Override
    public void draw(
            GraphicsContext gc,
            Rectangle rectangle,
            RestaurantTransform transform) {

        Point topLeft = transform.metersToPixels(
            rectangle.getXMin(),
            rectangle.getYMin()
        );

        Point bottomRight = transform.metersToPixels(
            rectangle.getXMax(),
            rectangle.getYMax()
        );

        gc.strokeRect(
            topLeft.getX(),
            topLeft.getY(),
            bottomRight.getX() - topLeft.getX(),
            bottomRight.getY() - topLeft.getY()
        );
    }
}