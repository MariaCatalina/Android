package com.example.cata.clientweather.weatherapp;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cata.clientweather.R;
import com.example.cata.clientweather.server.ApiAdapter;
import com.example.cata.clientweather.server.models.WeatherData;
import com.example.cata.clientweather.server.responses.WeatherResponse;
import com.example.cata.clientweather.weatherapp.adaptors.AllDaysAdaptor;
import com.example.cata.clientweather.weatherapp.adaptors.DayWeatherAdaptor;
import com.example.cata.clientweather.weatherapp.models.AllDaysData;
import com.example.cata.clientweather.weatherapp.models.DayWeatherData;
import com.github.leonardoxh.fakesearchview.FakeSearchView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements FakeSearchView.OnSearchListener {

    private static final String TAG = MainActivity.class.toString();

    private List<DayWeatherData> dayWeatherData = new ArrayList<>();
    private List<AllDaysData> daysDetails = new ArrayList<>();

    private DayWeatherAdaptor dayAdaptor;
    private AllDaysAdaptor allDaysAdaptor;

    private RecyclerView recyclerView;
    private RecyclerView allDaysRecyclerView;

    private String daySelected;

    private TextView title;

    private static final int KELVIN_DEGREES = 273;
    private static final int INFINITY = 10000;
    private static final int MINUS_INFINITY = -10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set recycler view current day
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        dayAdaptor = new DayWeatherAdaptor(dayWeatherData);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dayAdaptor);

        // set days recycler list view
        allDaysRecyclerView = (RecyclerView) findViewById(R.id.recyclerDays);

        allDaysAdaptor = new AllDaysAdaptor(daysDetails);

        RecyclerView.LayoutManager layoutManagerAllDays = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allDaysRecyclerView.setLayoutManager(layoutManagerAllDays);
        allDaysRecyclerView.setAdapter(allDaysAdaptor);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDay = new Date();

        daySelected = dateFormat.format(currentDay);
        title = (TextView)

                findViewById(R.id.progmnoza);

        String titleText = "FORECAST " + daySelected;
        title.setText(titleText);

    }

    private void getNewWeatherData(String city){
        ApiAdapter.getInstance().

                getWeatherData(city, new Callback<WeatherResponse>() {

                            @Override
                            public void onResponse
                                    (Call<WeatherResponse> call, Response<WeatherResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    WeatherResponse weatherResponse = response.body();
                                    Log.i(TAG, "onResponse: positive" + weatherResponse);

                                    setCurrentDayDetails(weatherResponse);
                                    setAllDaysData(weatherResponse);

                                } else {
                                    Log.i(TAG, "onResponse: error " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                                Log.i(TAG, "onFailure: " + call.toString());
                            }
                        }

                );
    }

    private void setAllDaysData(final WeatherResponse weatherResponse) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<WeatherData> dataList = weatherResponse.getWeatherData();
                String currentDay = daySelected;

                for (int i = 0; i < 5; i++) {

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;

                    try {
                        date = format.parse(currentDay);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);

                        cal.add(Calendar.DATE, 1);

                        Log.i(TAG, "run: get next day" + format.format(cal.getTime()));
                        int day = cal.get(Calendar.DAY_OF_WEEK);

                        daysDetails.add(new AllDaysData(getDayOfWeek(day), getDayMinTemp(dataList, currentDay), getDayMaxTemp(dataList, currentDay), getDescription(dataList, currentDay)));

                        Log.i(TAG, "run: " + daysDetails.get(0).toString());
                        currentDay = format.format(cal.getTime());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                allDaysAdaptor.notifyDataSetChanged();
            }
        });

    }

    /**
     * method calculate the minimum temperature for selected day
     *
     * @param weatherDataList
     * @param day
     * @return
     */
    private String getDayMinTemp(List<WeatherData> weatherDataList, String day) {
        float tempMin = INFINITY;
        for (WeatherData data : weatherDataList) {
            if (data.getDataAndTime().contains(day)) {
                if (tempMin > data.getTempData().getTemp_min())
                    tempMin = data.getTempData().getTemp_min();
            }
        }

        tempMin -= KELVIN_DEGREES;

        return String.valueOf((int) tempMin) + "°";
    }

    /**
     * method calculate the maximum temperature for selected day
     *
     * @param weatherDataList
     * @param day
     * @return
     */
    private String getDayMaxTemp(List<WeatherData> weatherDataList, String day) {
        float tempMax = MINUS_INFINITY;
        for (WeatherData data : weatherDataList) {
            if (data.getDataAndTime().contains(day)) {
                if (tempMax < data.getTempData().getTemp_min())
                    tempMax = data.getTempData().getTemp_min();
            }
        }

        tempMax -= KELVIN_DEGREES;

        return String.valueOf((int) tempMax) + "°";
    }

    private String getDescription(List<WeatherData> weatherDataList, String day){
        for (WeatherData data : weatherDataList) {
            if (data.getDataAndTime().contains(day))
                return data.getWeather().get(0).getDescription();
        }
        return "";
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    private void setCurrentDayDetails(final WeatherResponse weatherResponse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                List<WeatherData> dataList = weatherResponse.getWeatherData();

                for (WeatherData data : dataList) {
                    if (data.getDataAndTime().contains(daySelected)) {
                        Log.i(TAG, "parseRequest: found day: " + data);
                        String[] stringSplit = data.getDataAndTime().split(" ");

                        float temperature = Math.round(data.getTempData().getTemp() - KELVIN_DEGREES);
                        String degrees = String.valueOf((int) temperature) + "°";

                        Log.i(TAG, "parseRequest: " + degrees);

                        // extract hour and minutes HH-MM
                        String[] hour = stringSplit[1].split(":");
                        String hourResult = hour[0] + ":" + hour[1];

                        dayWeatherData.add(new DayWeatherData(hourResult, degrees, data.getWeather().get(0).getWeatherData()));
                    }
                }

                Log.i(TAG, "parseRequest: " + dayWeatherData);
                dayAdaptor.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.fake_search);

        FakeSearchView fakeSearchView = (FakeSearchView) MenuItemCompat.getActionView(menuItem);
        fakeSearchView.setOnSearchListener(this);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar item clicks here. It'll
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.fake_search:
                // Open the search view on the menu item click.

                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onSearch(FakeSearchView fakeSearchView, CharSequence constraint) {
        //((Filterable)listView.getAdapter()).getFilter().filter(constraint);
        Log.i(TAG, "onSearch: " + fakeSearchView.getSearchText().toString());

        String city = fakeSearchView.getSearchText().toString();
        if (city.contains("*")) {

            daysDetails.clear();
            dayWeatherData.clear();

            String searchCity[] = city.split(" ");
            getNewWeatherData(searchCity[0]);
        }
    }

    @Override
    public void onSearchHint(FakeSearchView fakeSearchView, CharSequence constraint) {

    }
}
