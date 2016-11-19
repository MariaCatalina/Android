package com.example.cata.bullsandcows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.cata.bullsandcows.enums.Logs;
import com.orhanobut.simplelistview.SimpleListView;

/**
 * Created by Cata on 11/16/2016.
 */

public class WinnersActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winners_list);

        /* enable back button */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Winners winnersList = Winners.getInstance();

        /*  */
        SimpleListView listView = (SimpleListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                winnersList.generateString()
        );

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                Log.v(Logs.BACKBUTTON.toString(), "back button pressed");

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
