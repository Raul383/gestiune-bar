package com.restaurant.utils;

import javafx.scene.control.Slider;
import javafx.scene.control.Label;

// clasa scrisa in 24 NOV BIG 26
public final class SliderUtils {
    
    // constructor scris in 24 NOV BIG 26
    private SliderUtils() {}

    // metoda scrisa in 24 NOV BIG 26
    //adauga listenerele necesare
    public static void bindValueToLabel(
            Slider slider,
            Label label,
            String format) {

        slider.valueProperty().addListener((obs, oldV, newV) ->
            label.setText(String.format(format, newV.doubleValue()))
        );

        // Setăm și valoarea inițială
        label.setText(String.format(format, slider.getValue()));
    }
}