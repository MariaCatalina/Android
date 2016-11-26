package com.example.cata.customlistview.models;

import java.util.List;

/**
 * Created by Cata on 11/23/2016.
 */

public class MonthData {

    private String month;
    private double kilometers;
    private String timeVsKm;
    private int health;

    private int monthNumber;

    private List<DayData> dayData;

    public MonthData(int monthNumber, String month, double kilometers, String timeVsKm, int health, List<DayData> daydata) {
        this.month = month;
        this.kilometers = kilometers;
        this.timeVsKm = timeVsKm;
        this.health = health;
        this.dayData = daydata;
    }

    public MonthData(int monthNumber, String month, double kilometers, String timeVsKm, int health) {
        this.monthNumber = monthNumber;
        this.month = month;
        this.kilometers = kilometers;
        this.timeVsKm = timeVsKm;
        this.health = health;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public String getTimeVsKm() {
        return timeVsKm;
    }

    public void setTimeVsKm(String timeVsKm) {
        this.timeVsKm = timeVsKm;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
