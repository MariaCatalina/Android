package com.example.cata.bullsandcows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.cata.bullsandcows.adaptors.ListWinnersAdaptor;
import com.example.cata.bullsandcows.enums.Logs;
import com.example.cata.bullsandcows.models.User;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Cata on 11/16/2016.
 */

public class WinnersActivity extends AppCompatActivity {

    private UsersData userList = UsersData.getInstance();

    private RecyclerView recyclerView;
    private ListWinnersAdaptor listAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winners_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* sort list */
        Collections.sort(userList.getUsers(), new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getScore() - o2.getScore();
            }
        });

        listAdaptor = new ListWinnersAdaptor(userList.getUsers(), WinnersActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdaptor);

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
