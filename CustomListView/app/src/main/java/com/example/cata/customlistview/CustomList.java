package com.example.cata.customlistview;

import com.example.cata.customlistview.models.DayData;
import com.example.cata.customlistview.models.MonthData;

/**
 * Created by Cata on 11/26/2016.
 */

public class CustomList {
    private MonthData monthData;
    private DayData dayData;

    public CustomList(MonthData monthData, DayData dayData) {
        this.monthData = monthData;
        this.dayData = dayData;
    }

    public MonthData getMonthData() {
        return monthData;
    }

    public DayData getDayData() {
        return dayData;
    }

}
