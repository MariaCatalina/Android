package com.example.cata.bullsandcows;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.cata.bullsandcows.enums.IntentKey;
import com.gregacucnik.EditTextView;
import com.spark.submitbutton.SubmitButton;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rand;
    private String user;

    private Button startGameButton;
    private Button showWinners;

    private SubmitButton validateButton;

    private EditTextView userName;

    private final String fileName = "userData.txt";
    Winners winnersList = Winners.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditTextView) findViewById(R.id.userName);
        startGameButton = (Button) findViewById(R.id.startGame);
        showWinners = (Button) findViewById(R.id.winnersList);

        /*  */
        validateButton = (SubmitButton) findViewById(R.id.validateUser);

        /* user verification */
        validateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user = userName.getText();
                if (user.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter a valid user", Toast.LENGTH_LONG).show();
                } else if (winnersList.contains(user)) {

                    Log.v("new user", user);

                    DialogFragment newFragment = new OverrideUsername();
                    newFragment.show(getFragmentManager(), "missiles");


                } else if (!winnersList.contains(user)) {

                    String data = user + ":" + 0;

                    Log.v("Write in file:", data);
                    writeToFile(data, getApplicationContext());


                    winnersList.addUser(user, 0);
                    startGameButton.setEnabled(true);

                    userName.setText("");
                    Toast.makeText(getApplicationContext(), "User valid", Toast.LENGTH_LONG).show();
                }
            }
        });

        rand = new Random();
        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartGame.class);

                /* generate the secret number */
                int secretNumber = rand.nextInt(10000) + 1;
                intent.putExtra(IntentKey.SECRETNUMBER.toString(), String.valueOf(secretNumber));
                intent.putExtra(IntentKey.USERNAME.toString(), user);

                startActivity(intent);

                /* inactive the buttons */
                startGameButton.setEnabled(false);
                validateButton.refreshDrawableState();
            }
        });


        /* read from file */

        showWinners.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String read = readFromFile(getApplicationContext());
                Log.v("from file:", read);

                Intent intent = new Intent(MainActivity.this, WinnersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
