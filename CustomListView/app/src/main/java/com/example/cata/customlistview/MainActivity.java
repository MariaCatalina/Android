package com.example.cata.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cata.customlistview.models.DayData;
import com.example.cata.customlistview.models.MonthData;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.custom_list_view);


        ArrayList<CustomList> customElements = new ArrayList<>();

        customElements.add(new CustomList(new MonthData(12, "December", 7.72, "8'58''", 1876), null));
        customElements.add(new CustomList(null, new DayData("7.72km", "8'58''", "1:09:18", "TODAY" )));
        customElements.add(new CustomList(null, new DayData("5.00km", "6'30''", "45:20", "11/12/2016" )));

        customElements.add(new CustomList(new MonthData(11, "November", 44.86, "6'53''", 11061), null));
        customElements.add(new CustomList(null, new DayData("6.34km", "6.56", "43:37", "23/11/2016")));
        customElements.add(new CustomList(null, new DayData("11.81km", "6.34", "1:17:36", "19/11/2016")));
        customElements.add(new CustomList(null, new DayData("5.90km", "6.12", "36:36", "16/11/2016")));
        customElements.add(new CustomList(null, new DayData("3.40km", "5.24", "20:06", "10/11/2016")));

        customElements.add(new CustomList(new MonthData(10, "October", 10.40, "8'23''", 429), null));
        customElements.add(new CustomList(null, new DayData("10.40km", "8'23", "30:06", "28/10/2016")));

        listView.setAdapter(new CustomAdapter(this, customElements));
    }
}
