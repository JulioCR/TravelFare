/**
 * Calculated fare information
 */

package com.funtravel.travelfare.model;

public class DriverCalculatedFare {

    public String driverName;

    public String vehicleType;

    public int calculatedFare;

    public DriverCalculatedFare(String driverName, String vehicleType, int calculatedFare) {
        this.driverName = driverName;
        this.vehicleType = vehicleType;
        this.calculatedFare = calculatedFare;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getCalculatedFare() {
        return calculatedFare;
    }

}
