/**
 * Singleton data class to act as a database and communication between JavaFX stages.
 */

package com.funtravel.travelfare.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataDriverSingleton {

    private static final DataDriverSingleton INSTANCE_DATA_DRIVER_SINGLETON = new DataDriverSingleton();

    private ObservableList<Driver> driverList = FXCollections.observableArrayList();

    private int selectedIndex;

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    private DataDriverSingleton(){}

    public static DataDriverSingleton getInstance(){
        return INSTANCE_DATA_DRIVER_SINGLETON;
    }

    public ObservableList<Driver> getDriverList() {
        return driverList;
    }

    public void addDriver(Driver driver){
        this.driverList.add(driver);
    }

    public Driver getDriver(int index) { return this.driverList.get(index); }

    public void setDriver(Driver driver, int index) { this.driverList.set(index, driver); }

    public void deleteDriver(int index) { this.driverList.remove(index); }

}
