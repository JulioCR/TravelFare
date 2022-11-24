/**
 * Main running class program
 * @author Julio Rodriguez
 */

package com.funtravel.travelfare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelFare extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/funtravel/travelfare/fxml/driver-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Driver Fare Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}