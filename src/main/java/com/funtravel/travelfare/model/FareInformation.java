/**
 * Fare Information from CSV file
 */

package com.funtravel.travelfare.model;

public class FareInformation {

    private int distanceTraveled;

    private int traveledUnit;

    private int costPerDistanceTraveled;

    public FareInformation(int distanceTraveled, int traveledUnit, int costPerDistanceTraveled) {
        this.distanceTraveled = distanceTraveled;
        this.traveledUnit = traveledUnit;
        this.costPerDistanceTraveled = costPerDistanceTraveled;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public int getTraveledUnit() {
        return traveledUnit;
    }

    public int getCostPerDistanceTraveled() {
        return costPerDistanceTraveled;
    }

}
