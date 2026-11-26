
package com.restaurant.renderer;

import com.restaurant.model.HartaRestaurant;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RestaurantRenderer {


    public static void drawRestaurant(
            Canvas canvas,
            HartaRestaurant restaurant,
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
    }
}
