package com.solvd.project.model;

import java.time.LocalDate;

public class WeatherConditions {
    private int weatherId;
    private String condition;
    private double temperature;
    private LocalDate dateReported;

    public WeatherConditions(int weatherId, String condition, double temperature, LocalDate dateReported) {
        this.weatherId = weatherId;
        this.condition = condition;
        this.temperature = temperature;
        this.dateReported = dateReported;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public LocalDate getDateReported() {
        return dateReported;
    }

    public void setDateReported(LocalDate dateReported) {
        this.dateReported = dateReported;
    }
}
