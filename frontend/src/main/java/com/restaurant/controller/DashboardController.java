package com.restaurant.controller;

import com.restaurant.App;
import com.restaurant.api.DashboardApi;
import com.restaurant.renderer.DashboardRenderer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;

    private final DashboardApi api = new DashboardApi();
    private final DashboardRenderer renderer = new DashboardRenderer();

    @FXML
    private void initialize() {
        renderer.render(api.loadContent(), titleLabel, descriptionLabel);
    }

    @FXML
    private void openInterfataMese() throws IOException {
        App.setRoot("interfataMese");
    }

    @FXML
    private void openInterfata1() throws IOException {
        App.setRoot("Interfata1");
    }

    @FXML
    private void openInterfata2() throws IOException {
        App.setRoot("Interfata2");
    }
}
