
package com.restaurant.renderer;

import com.restaurant.model.HartaRestaurant;
import com.restaurant.model.Shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class RestaurantRenderer {

    private static final CircleRender CIRCLE_RENDER = new CircleRender();
    private static final RectangleRender RECTANGLE_RENDER = new RectangleRender();

    // metoda scrisa in 26 NOV 2026
    public static void drawShape(
            GraphicsContext gc,
            Shape shape,
            RestaurantTransform transform) {

        if (shape instanceof com.restaurant.model.Circle circle) {
            CIRCLE_RENDER.draw(gc, circle, transform);
        } else if (shape instanceof com.restaurant.model.Rectangle rectangle) {
            RECTANGLE_RENDER.draw(gc, rectangle, transform);
        }
    }

    public static void drawRestaurant(
            Canvas canvas,
            HartaRestaurant restaurant,
             RestaurantTransform transform,
            double margin) {

        double lungime = restaurant.getLungime();
        double latime = restaurant.getInaltime();

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();


        double availableWidth = canvasWidth - 2 * margin;
        double availableHeight = canvasHeight - 2 * margin;

        // Scara: pixeli pentru un metru
        double scale = Math.min(
            availableWidth / lungime,
            availableHeight / latime
        );

        // Dimensiunile restaurantului în pixeli
        double pixelWidth = lungime * scale;
        double pixelHeight = latime * scale;

        // Centrare în Canvas
        double x = (canvasWidth - pixelWidth) / 2;
        double y = (canvasHeight - pixelHeight) / 2;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Curățăm Canvas-ul
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // Stil contur
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // Desenăm restaurantul
        gc.strokeRect(x, y, pixelWidth, pixelHeight);

        // Desenăm obstacolele cu roșu
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);

        for (Shape obstacol : restaurant.getObstacole()) {
            drawShape(gc, obstacol, transform);
        }

        // Desenăm mesele cu albastru
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        for (Shape masa : restaurant.getMese()) {
            drawShape(gc, masa, transform);
        }

    }


}
