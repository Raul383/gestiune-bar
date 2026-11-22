package com.restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class InterfataMeseController {

    @FXML private AnchorPane canvasPane;
    @FXML private VBox menuPanel;
    @FXML private Label infoLabel;

    @FXML private ToggleButton btnSelect;
    @FXML private ToggleButton btnDraw;
    @FXML private Button btnResize;
    @FXML private Button btnSave;

    @FXML private VBox drawOptionsPanel;
    @FXML private ToggleButton btnCircle;
    @FXML private ToggleButton btnRectangle;

    @FXML private VBox circleParams;
    @FXML private VBox rectangleParams;
    @FXML private Slider sliderRaza;
    @FXML private Slider sliderLungime;
    @FXML private Slider sliderLatime;
    @FXML private Label labelRaza;
    @FXML private Label labelLungime;
    @FXML private Label labelLatime;

    @FXML private RadioButton radioMasa;
    @FXML private RadioButton radioObstacol;

    private final ToggleGroup modeGroup = new ToggleGroup();
    private final ToggleGroup shapeGroup = new ToggleGroup();
    private final ToggleGroup tipGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        // Grupăm butoanele Select și Draw ca să fie mutual exclusive
        btnSelect.setToggleGroup(modeGroup);
        btnDraw.setToggleGroup(modeGroup);
        btnSelect.setSelected(true); // Select este modul implicit

        // Grupăm butoanele de formă
        btnCircle.setToggleGroup(shapeGroup);
        btnRectangle.setToggleGroup(shapeGroup);
        btnCircle.setSelected(true); // Cerc este forma implicită

        // legările pentru butoane pătrate
        btnSelect.prefHeightProperty().bind(btnSelect.prefWidthProperty());
        btnDraw.prefHeightProperty().bind(btnDraw.prefWidthProperty());
        btnResize.prefHeightProperty().bind(btnResize.prefWidthProperty());
        btnSave.prefHeightProperty().bind(btnSave.prefWidthProperty());
        btnCircle.prefHeightProperty().bind(btnCircle.prefWidthProperty());
        btnRectangle.prefHeightProperty().bind(btnRectangle.prefWidthProperty());

        // Grup nou: Masă / Obstacol
        radioMasa.setToggleGroup(tipGroup);
        radioObstacol.setToggleGroup(tipGroup);
        radioMasa.setSelected(true);

        // Ascultăm schimbările modului pentru a arăta/ascunde panoul de desen
        modeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            boolean drawMode = newToggle == btnDraw;
            drawOptionsPanel.setVisible(drawMode);
            drawOptionsPanel.setManaged(drawMode); // managed=false => nu ocupă spațiu când e ascuns
        });

        // Ascultăm schimbările formei selectate
        shapeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == btnCircle) {
                infoLabel.setText("Formă selectată: Cerc");
            } else if (newToggle == btnRectangle) {
                infoLabel.setText("Formă selectată: Pătrat");
            }
        });

                // 4. Formă: Cerc vs Pătrat
        shapeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == btnCircle) {
                circleParams.setVisible(true);
                circleParams.setManaged(true);
                rectangleParams.setVisible(false);
                rectangleParams.setManaged(false);
            } else if (newToggle == btnRectangle) {
                circleParams.setVisible(false);
                circleParams.setManaged(false);
                rectangleParams.setVisible(true);
                rectangleParams.setManaged(true);
            } else {
                // niciun buton selectat (sau formă necunoscută) -> default: ascunde ambele
                circleParams.setVisible(false);
                circleParams.setManaged(false);
                rectangleParams.setVisible(false);
                rectangleParams.setManaged(false);
            }
        });

            // Listener pentru feedback
        tipGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == radioMasa) {
                infoLabel.setText("Tip selectat: Masă");
            } else if (newToggle == radioObstacol) {
                infoLabel.setText("Tip selectat: Obstacol");
            }
        });

                // 5. Slider-e: actualizează label-urile în timp real
        sliderRaza.valueProperty().addListener((obs, oldV, newV) ->
            labelRaza.setText(String.format("%.1f m", newV.doubleValue()))
        );
        sliderLungime.valueProperty().addListener((obs, oldV, newV) ->
            labelLungime.setText(String.format("%.1f m", newV.doubleValue()))
        );
        sliderLatime.valueProperty().addListener((obs, oldV, newV) ->
            labelLatime.setText(String.format("%.1f m", newV.doubleValue()))
        );

        infoLabel.setText("Mod: Select. Alege o formă din canvas.");
    }
}