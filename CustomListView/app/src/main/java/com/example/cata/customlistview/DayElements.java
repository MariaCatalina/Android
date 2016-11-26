package com.example.cata.customlistview;

import com.example.cata.customlistview.models.DayData;

import java.util.ArrayList;

/**
 * Created by Cata on 11/25/2016.
 */

public class DayElements {

    private ArrayList<DayData> dayData;
    private int position;

    public DayElements(ArrayList<DayData> dayData, int position) {
        this.dayData = dayData;
        this.position = position;
    }

    public ArrayList<DayData> getDayData() {
        return dayData;
    }

    public void setDayData(ArrayList<DayData> dayData) {
        this.dayData = dayData;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DayData getDay(){

        if (position < dayData.size()) {
            DayData result = dayData.get(position);
            position++;
            return result;
        }

        else
            return null;
    }

    public boolean hasNextElement(){
        if (position < dayData.size())
            return true;

        return false;
    }
}
