/**
 * Controller for the calculated driver fare panel
 */

package com.funtravel.travelfare.controller;

import com.funtravel.travelfare.model.DataDriverSingleton;
import com.funtravel.travelfare.model.Driver;
import com.funtravel.travelfare.model.DriverCalculatedFare;
import com.funtravel.travelfare.model.FareInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DriverFareController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private Button btnFareSave;
    @FXML
    private TableView<DriverCalculatedFare> tblDriverFare;
    @FXML
    private TableColumn colDriverName;
    @FXML
    private TableColumn colVehicleType;
    @FXML
    private TableColumn colTotalFare;
    @FXML
    private Label lblDistanceTraveled;
    @FXML
    private Label lblTraveledUnit;
    @FXML
    private Label lblCostPerDistanceTraveled;

    DataDriverSingleton data = DataDriverSingleton.getInstance();

    private ObservableList<DriverCalculatedFare> driverFareList = FXCollections.observableArrayList();

    /**
     * When the panel is initialized: Read the CSV file, get the information from it and calculate the fares, and present it in the table
     * @param url Initialize parameter
     * @param resourceBundle Initialize parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        this.colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        this.colTotalFare.setCellValueFactory(new PropertyValueFactory("calculatedFare"));

        FareInformation fareData = csvReader();

        lblDistanceTraveled.setText(String.valueOf(fareData.getDistanceTraveled()));
        lblTraveledUnit.setText(String.valueOf(fareData.getTraveledUnit()));
        lblCostPerDistanceTraveled.setText(String.valueOf(fareData.getCostPerDistanceTraveled()));


        for(Driver driver : data.getDriverList()){
            String driverName = driver.getName() + " " + driver.getSurname();
            String vehicleType = driver.getVehicleType();
            int totalFare = calculateFare(driver.getBaseFarePrice(), driver.getBaseFareDistance(), fareData.getDistanceTraveled(), fareData.getTraveledUnit(), fareData.getCostPerDistanceTraveled());
            driverFareList.add(new DriverCalculatedFare(driverName,vehicleType,totalFare));
        }

        tblDriverFare.setItems(driverFareList);
        tblDriverFare.getSortOrder().add(colTotalFare);

    }

    /**
     * OK button to return to previous panel
     * @param event Action Event Click
     */
    @FXML
    void btnOkClick(ActionEvent event) {

        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();

    }

    /**
     * Save button to export the information presented here in a CSV file
     * @param event Action Event Click
     */
    @FXML
    void btnFareSaveClick(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(btnFareSave.getScene().getWindow());

        if (file != null){
            writeCSVFile(file);
        }

    }

    /**
     * @return FareInformation containing the read CSV file information
     */
    FareInformation csvReader () {

        String csvRoute = "src/main/resources/com/funtravel/travelfare/csv/fare-information.csv";
        String line;
        int distanceTraveled = 0;
        int traveledUnit = 0;
        int costPerDistance = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(csvRoute));
            while ((line = br.readLine()) != null) {
                String[] csvReadFareLine = line.split(",");
                distanceTraveled = Integer.parseInt(csvReadFareLine[0]);
                traveledUnit = Integer.parseInt(csvReadFareLine[1]);
                costPerDistance = Integer.parseInt(csvReadFareLine[2]);
            }

        } catch (IOException e){

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error with CSV input file.\nPlease check the CSV file.");
            alert.showAndWait();
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();

        }

        return new FareInformation(distanceTraveled, traveledUnit, costPerDistance);

    }

    /**
     * Formula to calculate the final fare price
     * @param baseFarePrice Base fare price acquired from driver
     * @param baseFareDistance Base fare distance acquired from driver
     * @param distanceTraveled Distance traveled acquired from the CSV file
     * @param traveledUnit Traveled unit acquired from the CSV file
     * @param costPerDistance Cost per distance acquired from the CSV file
     * @return the calculated fare
     */
    int calculateFare(int baseFarePrice, int baseFareDistance, int distanceTraveled, int traveledUnit, int costPerDistance){

        float chargedDistance = distanceTraveled - baseFareDistance;

        if (chargedDistance > 0){

            float distanceFraction = chargedDistance / traveledUnit;
            return baseFarePrice + (Math.round(distanceFraction * costPerDistance));

        } else {
            return baseFarePrice;
        }

    }

    /**
     * Writing the CSV file when the Save button function is called
     * @param file File given to write data to
     */
    void writeCSVFile(File file){

        String header = "Driver Name, Vehicle Type, Total Fare";

        try {

            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(header);

            for (DriverCalculatedFare driverCalculatedFare : driverFareList) {
                writer.println(driverCalculatedFare.getDriverName() + ", " + driverCalculatedFare.getVehicleType() + ", " + driverCalculatedFare.getCalculatedFare());
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Fatal error writing the CSV file.");
            alert.showAndWait();
        }


    }

}
