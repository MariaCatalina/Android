package com.example.cata.clientweather.weatherapp.models;

/**
 * Created by Cata on 12/10/2016.
 */

public class AllDaysData {
    String dayName;

    String tempMin;
    String tempMax;
    String description;

    public AllDaysData(String dayName, String tempMin, String tempMax, String description) {
        this.dayName = dayName;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.description = description;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AllDaysData{" +
                "dayName='" + dayName + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
