package com.example.cata.customlistview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cata.customlistview.CustomList;
import com.example.cata.customlistview.R;
import com.example.cata.customlistview.models.DayData;
import com.example.cata.customlistview.models.MonthData;

import java.util.List;

/**
 * Created by Cata on 11/23/2016.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private final static int MONTH_TYPE = 0;
    private final static int DAY_TYPE = 1;
    private final static int ROW_TYPES_NUMBER = 2;

    private List<CustomList> customList;

    public CustomAdapter(Context context, List<CustomList> customList) {
        super();
        this.customList = customList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class MonthHolder {
        private TextView monthTextView;

        private ImageView monthHexagon;
        private TextView monthKilometers;

        private ImageView monthAvTimer;
        private TextView monthTimeVsKm;

        private ImageView monthPlusCircle;
        private TextView monthHealt;

        public MonthHolder(TextView monthTextView, ImageView monthHexagon, TextView monthKilometers, ImageView monthAvTimer, TextView monthTimeVsKm, ImageView monthPlusCircle, TextView monthHealt) {
            this.monthTextView = monthTextView;
            this.monthHexagon = monthHexagon;
            this.monthKilometers = monthKilometers;
            this.monthAvTimer = monthAvTimer;
            this.monthTimeVsKm = monthTimeVsKm;
            this.monthPlusCircle = monthPlusCircle;
            this.monthHealt = monthHealt;
        }
    }

    private static class DayHolder {
        private TextView dayDate;
        private TextView dayKilometers;

        private ImageView dayFlag;
        private ImageView smileIcon;
        ImageView roadIcon;
        ImageView sunIcon;

        ImageView avTimerIcon;
        TextView timeVsKm;

        ImageView timerIcon;
        TextView timeValue;

        public DayHolder(TextView dayDate, TextView dayKilometers, ImageView dayFlag, ImageView smileIcon, ImageView roadIcon, ImageView sunIcon, ImageView avTimerIcon, TextView timeVsKm, ImageView timerIcon, TextView timeValue) {
            this.dayDate = dayDate;
            this.dayKilometers = dayKilometers;
            this.dayFlag = dayFlag;
            this.smileIcon = smileIcon;
            this.roadIcon = roadIcon;
            this.sunIcon = sunIcon;
            this.avTimerIcon = avTimerIcon;
            this.timeVsKm = timeVsKm;
            this.timerIcon = timerIcon;
            this.timeValue = timeValue;
        }
    }

    @Override
    public int getCount() {
        return customList.size();
    }

    @Override
    public Object getItem(int i) {
        return customList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MonthHolder monthHolder;
        DayHolder dayHolder;

        int type = getItemViewType(i);

        if (type == MONTH_TYPE) {
            Log.v("CUSTOM ADAPTOR", "set layout for month");
            view = inflater.inflate(R.layout.month_layout, null);
            monthHolder = initializeMonthLayout(view);
            view.setTag(monthHolder);
        }

        if (type == DAY_TYPE) {
            Log.v("CUSTOM ADAPTOR", "set layout for day");

            view = inflater.inflate(R.layout.day_layout, null);
            dayHolder = initializeDayHolder(view);
            view.setTag(dayHolder);
        }

        if (type == MONTH_TYPE) {
            Log.v("CUSTOM ADAPTOR: ", "show month data");

            monthHolder = (MonthHolder) view.getTag();
            MonthData monthData = customList.get(i).getMonthData();

            monthHolder.monthTextView.setText(monthData.getMonth());
            monthHolder.monthHexagon.setImageResource(R.drawable.hexagon);
            monthHolder.monthKilometers.setText(String.valueOf(monthData.getKilometers()));

            monthHolder.monthAvTimer.setImageResource(R.drawable.av_timer);
            monthHolder.monthTimeVsKm.setText(monthData.getTimeVsKm());

            monthHolder.monthPlusCircle.setImageResource(R.drawable.plus_circle_outline);
            monthHolder.monthHealt.setText(String.valueOf(monthData.getHealth()));
        }

        if (type == DAY_TYPE) {
            Log.v("CUSTOM ADAPTOR: ", "show day data");

            dayHolder = (DayHolder) view.getTag();
            DayData dayData = customList.get(i).getDayData();

            dayHolder.dayDate.setText(dayData.getDate());
            dayHolder.dayKilometers.setText(dayData.getKilometers());

            dayHolder.dayFlag.setImageResource(R.drawable.flag_variant);
            dayHolder.smileIcon.setImageResource(R.drawable.smaile_icon);
            dayHolder.roadIcon.setImageResource(R.drawable.road_variant);
            dayHolder.sunIcon.setImageResource(R.drawable.white_balance_sunny);

            dayHolder.avTimerIcon.setImageResource(R.drawable.av_timer);
            dayHolder.timeVsKm.setText(dayData.getTimeVsKm());

            dayHolder.timerIcon.setImageResource(R.drawable.timer);
            dayHolder.timeValue.setText(dayData.getTime());
        }

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return ROW_TYPES_NUMBER;
    }

    @Override
    public int getItemViewType(int position) {
        if (customList.get(position).getMonthData() == null)
            return DAY_TYPE;

        return MONTH_TYPE;
    }

    /**
     * initialize month holder
     * @param view
     * @return
     */
    public MonthHolder initializeMonthLayout(View view) {

        TextView monthTextView = (TextView) view.findViewById(R.id.month);

        ImageView monthHexagon = (ImageView) view.findViewById(R.id.hexagon);
        TextView monthKilometers = (TextView) view.findViewById(R.id.kilometers);

        ImageView monthAvTimer = (ImageView) view.findViewById(R.id.avTimer);
        TextView monthTimeVsKm = (TextView) view.findViewById(R.id.timeVsKm);

        ImageView monthPlusCircle = (ImageView) view.findViewById(R.id.circle);
        TextView monthHealt = (TextView) view.findViewById(R.id.healt);

        return new MonthHolder(monthTextView, monthHexagon, monthKilometers, monthAvTimer, monthTimeVsKm, monthPlusCircle, monthHealt);

    }

    /**
     * initialize day holder
     * @param view
     * @return
     */
    public DayHolder initializeDayHolder(View view) {

        TextView dayDate = (TextView) view.findViewById(R.id.date);
        TextView dayKilometers = (TextView) view.findViewById(R.id.kilometers);

        ImageView dayFlag = (ImageView) view.findViewById(R.id.flag);
        ImageView smileIcon = (ImageView) view.findViewById(R.id.smileIcon);
        ImageView roadIcon = (ImageView) view.findViewById(R.id.roadIcon);
        ImageView sunIcon = (ImageView) view.findViewById(R.id.sunIcon);

        ImageView avTimerIcon = (ImageView) view.findViewById(R.id.avTimer);
        TextView timeVsKm = (TextView) view.findViewById(R.id.timeVsKm);

        ImageView timerIcon = (ImageView) view.findViewById(R.id.timerIcon);
        TextView timeValue = (TextView) view.findViewById(R.id.timeValue);

        return new DayHolder(dayDate, dayKilometers, dayFlag, smileIcon, roadIcon, sunIcon, avTimerIcon, timeVsKm, timerIcon, timeValue);

    }
}
