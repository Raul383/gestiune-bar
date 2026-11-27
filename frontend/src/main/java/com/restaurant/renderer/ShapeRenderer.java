package com.restaurant.renderer;

import com.restaurant.model.Shape;
import javafx.scene.canvas.GraphicsContext;

public interface ShapeRenderer<T extends Shape> {

    void draw(GraphicsContext gc, T shape, RestaurantTransform transform);
}