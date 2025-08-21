package com.solvd.project.model;

public class Drivers {
    private int driverId;
    private String license;
    private String experience;

    public Drivers(int driverId, String license, String experience) {
        this.driverId = driverId;
        this.license = license;
        this.experience = experience;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
