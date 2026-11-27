
package com.restaurant.renderer;

import com.restaurant.model.Point;

public class RestaurantTransform {

    private final double scale;
    private final double offsetX;
    private final double offsetY;

    public RestaurantTransform(
            double canvasWidth,
            double canvasHeight,
            double restaurantLength,
            double restaurantHeight,
            double margin) {

        if (canvasWidth <= 0 || canvasHeight <= 0 ||
            restaurantLength <= 0 || restaurantHeight <= 0 ||
            margin < 0) {
            throw new IllegalArgumentException(
                "Dimensiunile și marginea trebuie să fie valide."
            );
        }

        double availableWidth = canvasWidth - 2 * margin;
        double availableHeight = canvasHeight - 2 * margin;

        if (availableWidth <= 0 || availableHeight <= 0) {
            throw new IllegalArgumentException(
                "Marginea este prea mare pentru Canvas."
            );
        }

        // Factorul de scalare, păstrând proporțiile restaurantului.
        this.scale = Math.min(
            availableWidth / restaurantLength,
            availableHeight / restaurantHeight
        );

        double pixelWidth = restaurantLength * scale;
        double pixelHeight = restaurantHeight * scale;

        // Coordonatele colțului stânga-sus al restaurantului pe Canvas.
        this.offsetX = (canvasWidth - pixelWidth) / 2;
        this.offsetY = (canvasHeight - pixelHeight) / 2;
    }

    /**
     * Transformă coordonatele clickului din pixeli în metri.
     * Rotunjește rezultatul la cel mai apropiat centimetru.
     */
    public Point pixelsToMeters(double pixelX, double pixelY) {

        double x = (pixelX - offsetX) / scale;
        double y = (pixelY - offsetY) / scale;

        return new Point(
            roundToCentimeter(x),
            roundToCentimeter(y)
        );
    }

    //Metoda scrisa in 26 Nov Big 26 care transforma coordonatele din metrii in pixeli
    public Point metersToPixels(double x, double y) {
        return new Point(
            offsetX + x * scale,
            offsetY + y * scale
        );
    }

    //Metoda scrisa in 26 Nov Big 26 care transforma distantele din metrii in pixeli
    public double metersToPixelsDistance(double distance) {
        return distance * scale;
    }

    private double roundToCentimeter(double value) {
        return Math.round(value * 100.0) / 100.0;
    }


}
