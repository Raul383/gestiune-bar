package com.restaurant.controller;

import com.restaurant.App;
import com.restaurant.api.Interfata1Api;
import com.restaurant.renderer.Interfata1Renderer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
//cam asa o sa arate interfetele
public class Interfata1Controller {

    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;

    private final Interfata1Api api = new Interfata1Api();
    private final Interfata1Renderer renderer = new Interfata1Renderer();

    @FXML
    private void initialize() {
        renderer.render(api.loadContent(), titleLabel, descriptionLabel);
    }

    @FXML
    private void backToDashboard() throws IOException {
        App.setRoot("Dashboard");
    }
}
