package com.solvd.project.model;

public class Vehicles {
    private int vehicleId;
    private String model;
    private String registrationYear;
    private String vin;

    public Vehicles(int vehicleId, String model, String registrationYear, String vin) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.registrationYear = registrationYear;
        this.vin = vin;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(String registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
