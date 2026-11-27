package com.restaurant.controller;

import java.util.List;
import java.util.Map;

import com.restaurant.model.HartaRestaurant;
import com.restaurant.renderer.RestaurantRenderer;
import com.restaurant.renderer.RestaurantTransform;
import com.restaurant.utils.ButtonUtils;
import com.restaurant.utils.CanvasUtils;
import com.restaurant.utils.SliderUtils;
import com.restaurant.utils.ToggleUtils;

import com.restaurant.model.Point;
import com.restaurant.model.Shape;
import com.restaurant.model.Circle;
import com.restaurant.model.Rectangle;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private RestaurantTransform  restaurantTransform;

    private static final double RESTAURANT_MARGIN = 5;

    @FXML
    public void initialize() {

        hartaRestaurant = new HartaRestaurant(8, 12);


        CanvasUtils.bindSizeToPane(restaurantCanvas, canvasPane, this::redrawRestaurant);

        System.out.println("Canvas width: " + restaurantCanvas.getWidth());
        System.out.println("Canvas height: " + restaurantCanvas.getHeight());
        System.out.println("Restaurant length: " + hartaRestaurant.getLungime());
        System.out.println("Restaurant height: " + hartaRestaurant.getInaltime());

        restaurantCanvas.setOnMouseClicked(event -> {
            if (restaurantTransform == null) return;

            Point point = restaurantTransform.pixelsToMeters(
                event.getX(),
                event.getY()
            );

            if (modeGroup.getSelectedToggle() == btnDraw) {
                adaugaElement(point);
            } else if (modeGroup.getSelectedToggle() == btnSelect) {
                infoLabel.setText(
                    "X: " + point.getX() + " m | Y: " + point.getY() + " m"
                );
            }
        });

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
        SliderUtils.bindValueToLabel(sliderRaza, labelRaza, "%.2f m");
        SliderUtils.bindValueToLabel(sliderLungime, labelLungime, "%.2f m");
        SliderUtils.bindValueToLabel(sliderLatime, labelLatime, "%.2f m");

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

        restaurantTransform = new RestaurantTransform(
            restaurantCanvas.getWidth(),
            restaurantCanvas.getHeight(),
            hartaRestaurant.getLungime(),
            hartaRestaurant.getInaltime(),
            RESTAURANT_MARGIN
        );


        RestaurantRenderer.drawRestaurant(restaurantCanvas, hartaRestaurant, restaurantTransform, RESTAURANT_MARGIN);
    }

    private void adaugaElement(Point point) {
        Shape forma = creeazaForma(point);

        if (forma == null) {
            return;
        }

        int rezultat = adaugaInRestaurant(forma);

        redrawRestaurant();
        //gestioneazaRezultat(rezultat, forma, point);
    }

    private Shape creeazaForma(Point point) {
        Toggle formaSelectata = shapeGroup.getSelectedToggle();

        Shape returnedShape = null;

        if (formaSelectata == btnCircle) {
            double raza = sliderRaza.getValue();
            returnedShape = new Circle(point, raza);
        }

        if (formaSelectata == btnRectangle) {
            double lungime = sliderLungime.getValue();
            double inaltime = sliderLatime.getValue();

            returnedShape = new Rectangle(
                point.getX() - lungime / 2,
                point.getY() - inaltime / 2,
                point.getX() + lungime / 2,
                point.getY() + inaltime / 2
            );
        }

        return returnedShape;
    }

    private int adaugaInRestaurant(Shape forma) {
        Toggle tipSelectat = tipGroup.getSelectedToggle();

        if (tipSelectat == radioMasa) {
            return hartaRestaurant.addMasa(forma);
        }

        if (tipSelectat == radioObstacol) {
            return hartaRestaurant.addObstacol(forma);
        }

        return -1;
    }
/* 
    private void gestioneazaRezultat(int rezultat, Shape forma, Point point) {
        if (rezultat == 0) {
            afiseazaDetaliiElementAdaugat(forma, point);
            redrawRestaurant();
        } else {
            infoLabel.setText("Poziție invalidă! Elementul nu poate fi plasat aici.");
        }
    }

    private void afiseazaDetaliiElementAdaugat(Shape forma, Point point) {
        System.out.println("\n========== ELEMENT ADAUGAT ==========");
        System.out.printf(
            "Coordonate click: X = %.2f m, Y = %.2f m%n",
            point.getX(), point.getY()
        );

        if (forma instanceof Circle cerc) {
            System.out.println("Tip: Cerc");
            System.out.printf(
                "Centru: X = %.2f m, Y = %.2f m%n",
                cerc.getX(), cerc.getY()
            );
            System.out.printf("Raza: %.2f m%n", cerc.getRadius());

        } else if (forma instanceof Rectangle dreptunghi) {
            System.out.println("Tip: Dreptunghi");
            System.out.printf(
                "X minim: %.2f m | X maxim: %.2f m%n",
                dreptunghi.getXMin(), dreptunghi.getXMax()
            );
            System.out.printf(
                "Y minim: %.2f m | Y maxim: %.2f m%n",
                dreptunghi.getYMin(), dreptunghi.getYMax()
            );
        }

        System.out.printf(
            "Dimensiuni restaurant: X = %.2f m, Y = %.2f m%n",
            hartaRestaurant.getLungime(),
            hartaRestaurant.getInaltime()
        );
        System.out.println("=====================================\n");
    }
*/
}