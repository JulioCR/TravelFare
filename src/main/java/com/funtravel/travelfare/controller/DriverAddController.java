/**
 * Controller for the add driver panel
 */

package com.funtravel.travelfare.controller;

import com.funtravel.travelfare.model.DataDriverSingleton;
import com.funtravel.travelfare.model.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DriverAddController {

    @FXML
    private TextField txtSurname;
    @FXML
    private Button btnOk;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblName;
    @FXML
    private Label lblSurname;
    @FXML
    private TextField txtBaseFarePrice;
    @FXML
    private Label lblBaseFareDistance;
    @FXML
    private Label lblVehicleType;
    @FXML
    private TextField txtVehicleType;
    @FXML
    private Label lblBaseFarePrice;
    @FXML
    private TextField txtBaseFareDistance;


    DataDriverSingleton data = DataDriverSingleton.getInstance();

    /**
     * Confirm to add a new driver
     * @param event Action Event Click
     */
    @FXML
    void btnOkClick(ActionEvent event) {

        try {

            String name = this.txtName.getText();
            String surname = this.txtSurname.getText();
            String email = this.txtEmail.getText();
            String vehicleType = this.txtVehicleType.getText();
            int baseFarePrice = Integer.parseInt(this.txtBaseFarePrice.getText());
            int baseFareDistance = Integer.parseInt(this.txtBaseFareDistance.getText());

            Driver driver = new Driver(name, surname, email, vehicleType, baseFarePrice, baseFareDistance);
            data.addDriver(driver);

            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Incorrect values found.\nBase Fare Price and Base Fare Distance need numerical values.");
            alert.showAndWait();

        }

    }

    /**
     * Cancel adding a driver
     * @param event Action Event Click
     */
    @FXML
    void btnCancelClick(ActionEvent event) {

        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();

    }

}
