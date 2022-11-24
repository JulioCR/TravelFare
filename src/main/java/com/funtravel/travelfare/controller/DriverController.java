/**
 * Controller for the main driver panel
 */

package com.funtravel.travelfare.controller;

import com.funtravel.travelfare.model.DataDriverSingleton;
import com.funtravel.travelfare.model.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DriverController implements Initializable {

    @FXML
    private TableView<Driver> tblDriver;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colVehicleType;
    @FXML
    private TableColumn colBaseFarePrice;
    @FXML
    private TableColumn colBaseFareDistance;
    @FXML
    private Button btnDriverAdd;
    @FXML
    private Button btnDriverModify;
    @FXML
    private Button btnDriverDelete;
    @FXML
    private Button btnCalculateFare;

    DataDriverSingleton data = DataDriverSingleton.getInstance();


    /**
     * Initializing the panel
     * @param url Initialize Event
     * @param resourceBundle Initialize Event
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colName.setCellValueFactory(new PropertyValueFactory("name"));
        this.colSurname.setCellValueFactory(new PropertyValueFactory("surname"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        this.colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        this.colBaseFarePrice.setCellValueFactory(new PropertyValueFactory("baseFarePrice"));
        this.colBaseFareDistance.setCellValueFactory(new PropertyValueFactory("baseFareDistance"));

        tblDriver.setItems(data.getDriverList());

    }

    /**
     * Opens the Add Driver panel
     * @param event Action Event Click
     * @throws IOException FXML Loader Failure
     */
    @FXML
    void btnDriverAddClick(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/com/funtravel/travelfare/fxml/driver-add-view.fxml"));
        stage.setTitle("Add Driver");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    /**
     * Opens the Edit Driver panel
     * @param event Action Event Click
     * @throws IOException FXML Loader Failure
     */
    @FXML
    void btnDriverModifyClick(ActionEvent event) throws IOException {

        int driverSelectedIndex = this.tblDriver.getSelectionModel().getSelectedIndex();

        if (driverSelectedIndex != -1) {

            data.setSelectedIndex(driverSelectedIndex);

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/funtravel/travelfare/fxml/driver-edit-view.fxml"));
            stage.setTitle("Edit Driver");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Please select a driver in the table.");
            alert.showAndWait();

        }


    }

    /**
     * Opens the Delete Option to remove an entity from the table
     * @param event Action Event Click
     */
    @FXML
    void btnDriverDeleteClick(ActionEvent event) {

        int driverSelectedIndex = this.tblDriver.getSelectionModel().getSelectedIndex();

        if (driverSelectedIndex != -1) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Delete Driver");
            alert.setContentText("Are you sure you want to delete the selected driver?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                data.setSelectedIndex(this.tblDriver.getSelectionModel().getSelectedIndex());
                data.deleteDriver(data.getSelectedIndex());
                this.tblDriver.refresh();
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Please select a driver in the table.");
            alert.showAndWait();

        }

    }

    /**
     * Opens the calculation fare panel with the data in this table
     * @param event Action Event Click
     * @throws IOException FXML Loader Failure
     */
    @FXML
    void btnCalculateFareClick(ActionEvent event) throws IOException {

        if(data.getDriverList().size() == 0){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("There are no drivers in the table.");
            alert.showAndWait();

        } else {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/funtravel/travelfare/fxml/driver-fare-view.fxml"));
            stage.setTitle("Fare Calculation");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }

    }

}
