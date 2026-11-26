package com.restaurant.controller;

import java.util.Map;

import com.restaurant.model.HartaRestaurant;
import com.restaurant.renderer.RestaurantRenderer;
import com.restaurant.utils.ButtonUtils;
import com.restaurant.utils.SliderUtils;
import com.restaurant.utils.ToggleUtils;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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
    @FXML private Canvas restaurantCanvas;
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

    private HartaRestaurant hartaRestaurant;

    private static final double RESTAURANT_MARGIN = 5;

    @FXML
    public void initialize() {

        hartaRestaurant = new HartaRestaurant(8, 12);

        restaurantCanvas.widthProperty().bind(canvasPane.widthProperty());
        restaurantCanvas.heightProperty().bind(canvasPane.heightProperty());

        restaurantCanvas.widthProperty().addListener(
            (obs, oldWidth, newWidth) -> redrawRestaurant()
        );

        restaurantCanvas.heightProperty().addListener(
            (obs, oldHeight, newHeight) -> redrawRestaurant()
        );


        // Grupăm butoanele Select și Draw ca să fie mutual exclusive
        // utilizare dai grupul, dupa butonul default si dupa butoanele
        ButtonUtils.addToToggleGroup(modeGroup, btnSelect, btnSelect, btnDraw);

        // Grupăm butoanele de formă
        ButtonUtils.addToToggleGroup(shapeGroup, btnCircle, btnCircle, btnRectangle);

        // Grup nou: Masă / Obstacol
        ButtonUtils.addToToggleGroup( tipGroup, radioMasa, radioMasa, radioObstacol);

        // legările pentru butoane pătrate
        ButtonUtils.makeSquare( btnSelect, btnDraw, btnResize, btnSave, btnCircle, btnRectangle);


        // Ascultăm schimbările modului pentru a arăta/ascunde panoul de desen
        ToggleUtils.bindVisibilityToSelection(modeGroup, btnDraw, drawOptionsPanel);

        // Ascultăm schimbările formei selectate
        ToggleUtils.bindTextToSelection(shapeGroup, infoLabel,
            Map.of(
                btnCircle, "Formă selectată: Cerc",
                btnRectangle, "Formă selectată: Pătrat"
                ));

        // Listener pentru feedback
        ToggleUtils.bindTextToSelection(tipGroup, infoLabel,
            Map.of(
                radioMasa, "Tip selectat: Masă",
                radioObstacol, "Tip selectat: Obstacol"
            )
        );

        // 4. Formă: Cerc vs Pătrat (care e vizibila)

        ToggleUtils.bindVisibilityToSelection(shapeGroup,
            Map.of(
                btnCircle, circleParams,
                btnRectangle, rectangleParams
            )
        );

        // 5. Slider-e: actualizează label-urile în timp real
        SliderUtils.bindValueToLabel(sliderRaza, labelRaza, "%.1f m");
        SliderUtils.bindValueToLabel(sliderLungime, labelLungime, "%.1f m");
        SliderUtils.bindValueToLabel(sliderLatime, labelLatime, "%.1f m");

        infoLabel.setText("Mod: Select. Alege o formă din canvas.");
    }

    private void redrawRestaurant() {
        if (hartaRestaurant == null) {
            return;
        }

        if (restaurantCanvas.getWidth() <= 0 ||
            restaurantCanvas.getHeight() <= 0) {
            return;
        }

        RestaurantRenderer.drawRestaurant(restaurantCanvas, hartaRestaurant, RESTAURANT_MARGIN);
    }
}