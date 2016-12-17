package com.example.cata.clientweather.server.models;

/**
 * Created by Cata on 12/10/2016.
 */

public class Coordinates {
    private float lon;
    private float lat;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
