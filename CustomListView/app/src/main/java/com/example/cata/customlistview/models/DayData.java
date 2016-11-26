package com.example.cata.customlistview.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cata on 11/24/2016.
 */

public class DayData {

    //private Date date;
    //private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private String kilometers;
    private String timeVsKm;
    private String time;
    private String date;

    public DayData(String kilometers, String timeVsKm, String time, String date) {
        this.kilometers = kilometers;
        this.timeVsKm = timeVsKm;
        this.time = time;
        this.date = date;
    }

    public String getKilometers() {
        return kilometers;
    }

    public String getTimeVsKm() {
        return timeVsKm;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
