package com.example.cata.clientweather.server.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cata on 12/10/2016.
 */

public class City {
    private int id;
    @SerializedName("name")
    private String cityName;
    @SerializedName("coord")
    private Coordinates coordinates;
    private String country;
    private float population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", coordinates=" + coordinates +
                ", country='" + country + '\'' +
                ", population=" + population +
                '}';
    }
}
