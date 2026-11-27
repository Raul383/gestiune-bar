module com.restaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.restaurant to javafx.fxml;
    opens com.restaurant.controller to javafx.fxml;
    
    exports com.restaurant;
}
