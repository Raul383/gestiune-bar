package com.restaurant.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;


// Clasa scrisa in 26 NOV BIG 26
//  Clasa utilitara pentru a lega dimensiunile canvasului de dimensiunile anchor panului parinte.
//  Contine listenere pentru a schimba dimensiunea canvasului in functie de dimensiunea containerului parinte si la redimensionari da callback
//      pentru a redesena scena.

public final class CanvasUtils {

    //Constructor scris in 26 NOV BIG 26 
    // e privat deoarece e doar o clasa utilitara si nu vrem sa facem din gresala un obiect de acest tip
    private CanvasUtils() {
        
    }

    //Metoda scrisa in 26 NOV BIG 26
    //aceasta metoda leaga dimensiunea canvasului de dimensiunea Anchor pane-ului parinte si adauga listenere pentru resize

    public static void bindSizeToPane(
            Canvas canvas,
            Pane pane,
            Runnable onResize) {

        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());

        canvas.widthProperty().addListener(
            (obs, oldValue, newValue) -> onResize.run()
        );

        canvas.heightProperty().addListener(
            (obs, oldValue, newValue) -> onResize.run()
        );
    }
}
