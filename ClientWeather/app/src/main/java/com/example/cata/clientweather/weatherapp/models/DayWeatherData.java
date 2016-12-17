package com.example.cata.clientweather.weatherapp.models;

/**
 * Created by Cata on 12/10/2016.
 */

public class DayWeatherData {
    private String hour;
    private String grades;
    private String description;

    public DayWeatherData(String hour, String grades, String description) {
        this.hour = hour;
        this.grades = grades;
        this.description = description;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DayWeatherData{" +
                "hour='" + hour + '\'' +
                ", grades='" + grades + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
