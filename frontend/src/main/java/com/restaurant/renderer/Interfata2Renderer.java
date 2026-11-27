package com.restaurant.renderer;

import com.restaurant.model.ScreenContent;
import javafx.scene.control.Label;

public class Interfata2Renderer {

    public void render(ScreenContent content, Label titleLabel, Label descriptionLabel) {
        titleLabel.setText(content.title());
        descriptionLabel.setText(content.description());
    }
}
