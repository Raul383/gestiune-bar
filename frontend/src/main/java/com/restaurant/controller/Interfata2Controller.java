package com.restaurant.controller;

import com.restaurant.App;
import com.restaurant.api.Interfata2Api;
import com.restaurant.renderer.Interfata2Renderer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
//interfata 2 se inlocuieste cu ce vreti voi
public class Interfata2Controller {

    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;

    private final Interfata2Api api = new Interfata2Api();
    private final Interfata2Renderer renderer = new Interfata2Renderer();

    @FXML
    private void initialize() {
        renderer.render(api.loadContent(), titleLabel, descriptionLabel);
    }

    @FXML
    private void backToDashboard() throws IOException {
        App.setRoot("Dashboard");
    }
}
