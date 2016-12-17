package com.example.cata.clientweather.server.models;

/**
 * Created by Cata on 12/10/2016.
 */

public class TempData {
    private float temp;
    private float temp_min;
    private float temp_max;
    private float pressure;
    private float sea_level;
    private float grnd_level;
    private float humidity;
    private float temp_fk;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getSea_level() {
        return sea_level;
    }

    public void setSea_level(float sea_level) {
        this.sea_level = sea_level;
    }

    public float getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(float grnd_level) {
        this.grnd_level = grnd_level;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemp_fk() {
        return temp_fk;
    }

    public void setTemp_fk(float temp_fk) {
        this.temp_fk = temp_fk;
    }

    @Override
    public String toString() {
        return "TempData{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", sea_level=" + sea_level +
                ", grnd_level=" + grnd_level +
                ", humidity=" + humidity +
                ", temp_fk=" + temp_fk +
                '}';
    }
}
