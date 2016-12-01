package com.example.cata.bullsandcows;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cata.bullsandcows.enums.Keys;
import com.example.cata.bullsandcows.models.User;
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
    private EditTextView userNameTextView;

    private static final String fileName = "winners.txt";
    private UsersData userList = UsersData.getInstance();

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameTextView = (EditTextView) findViewById(R.id.userName);
        startGameButton = (Button) findViewById(R.id.startGame);
        showWinners = (Button) findViewById(R.id.winnersList);
        
        /* get users from file */
        prepareData();
        Log.i(TAG, "onCreate: data from file" + userList.getUsers().toString());

        /*  */
        validateButton = (SubmitButton) findViewById(R.id.validateUser);

        /* user verification */
        validateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user = userNameTextView.getText();
                if (user.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_SHORT).show();
                } else if (userList.contains(user)) {

                    Log.v("new user", user);

                    DialogFragment newFragment = new OverrideUsername();
                    newFragment.show(getFragmentManager(), "missiles");

                    /* send userName to dialog */
                    Bundle arguments = new Bundle();
                    arguments.putString(Keys.USERNAME.toString(), user);
                    newFragment.setArguments(arguments);

                    startGameButton.setEnabled(true);
                    userNameTextView.setText("");

                } else if (!userList.contains(user)) {

                    userList.addUser(new User(user, 0));

                    startGameButton.setEnabled(true);
                    userNameTextView.setText("");
                    Toast.makeText(getApplicationContext(), "User valid", Toast.LENGTH_SHORT).show();

                    validateButton.refreshDrawableState();
                }
            }
        });

        rand = new Random();
        startGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartGame.class);

                /* generate the secret number */
                int secretNumber = rand.nextInt(10000) + 1;
                intent.putExtra(Keys.SECRETNUMBER.toString(), String.valueOf(secretNumber));
                intent.putExtra(Keys.USERNAME.toString(), user);

                startActivity(intent);

                /* inactive the buttons */
                startGameButton.setEnabled(false);
                validateButton.refreshDrawableState();
            }
        });

        showWinners.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WinnersActivity.class);
                startActivity(intent);

                /* write data in file */
                writeToFile(userList.getUserDataFile(), MainActivity.this);
            }
        });
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, MainActivity.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * read data from file and parse to create the users
     */
    private void prepareData() {
        /* clean the list */
        userList.getUsers().clear();

        /* read from file and compose results */
        String fileData = readFromFile(getApplicationContext());
        Log.i(TAG, "prepareData: " + fileData);

        String[] parser = fileData.split("-");

        Log.i(TAG, "prepareData: " + parser);

        for(int i = 0; i < parser.length; i ++){
            String[] data = parser[i].split(":");
            userList.addUser(new User(data[0], Integer.valueOf(data[1])));
        }

        Log.i(TAG, "prepareData: " + userList);
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
