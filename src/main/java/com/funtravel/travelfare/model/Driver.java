/**
 * Driver model information
 */

package com.funtravel.travelfare.model;

public class Driver {

    private String name;
    private String surname;
    private String email;
    private String vehicleType;
    private int baseFarePrice;
    private int baseFareDistance;

    public Driver(String name, String surname, String email, String vehicleType, int baseFarePrice, int baseFareDistance) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.vehicleType = vehicleType;
        this.baseFarePrice = baseFarePrice;
        this.baseFareDistance = baseFareDistance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getBaseFarePrice() {
        return baseFarePrice;
    }

    public int getBaseFareDistance() {
        return baseFareDistance;
    }

}
