module com.funtravel.travelfare {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.funtravel.travelfare to javafx.fxml;
    opens com.funtravel.travelfare.controller to javafx.fxml;
    opens com.funtravel.travelfare.model to javafx.fxml;

    exports com.funtravel.travelfare;
    exports com.funtravel.travelfare.controller;
    exports com.funtravel.travelfare.model;
}