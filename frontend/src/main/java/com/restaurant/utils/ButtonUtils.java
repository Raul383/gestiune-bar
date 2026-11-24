package com.restaurant.utils;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

// clasa scrisa in 24 NOV BIG 26
public final class ButtonUtils {

    // constructor scris in 24 NOV BIG 26
    private ButtonUtils() {
        // Prevent instantiation
    }

    // metoda scrisa in 24 NOV BIG 26
    // face butoanele sa fie patrate
    public static void makeSquare(ButtonBase... buttons) {
        for (ButtonBase button : buttons) {
            button.prefHeightProperty()
                  .bind(button.widthProperty());
        }
    }

    // metoda scrisa in 24 NOV BIG 26
    // adauga butoane la toggle group 
    public static void addToToggleGroup(
        ToggleGroup group,
        Toggle defaultButton,
        Toggle... buttons) {

        for (Toggle button : buttons) {
            button.setToggleGroup(group);
        }

        if (defaultButton != null) {
            defaultButton.setSelected(true);
        }
    }
}
