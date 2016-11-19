package com.example.cata.bullsandcows;

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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rand;
    private String user;

    private Button startGameButton;
    private Button showWinners;

    private SubmitButton validateButton;

    private EditTextView userName;

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

                    //show popup
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.popup, null);

                    final PopupWindow popupWindow = new PopupWindow(popupView,
                                                                    ActionBar.LayoutParams.WRAP_CONTENT,
                                                                    ActionBar.LayoutParams.MATCH_PARENT, true);

                    popupWindow.setTouchable(true);
                    popupWindow.setFocusable(true);

                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                    Log.v("popup: ", String.valueOf(popupView.isShown()));

                    Button popupYes = (Button) popupView.findViewById(R.id.popupYes);
                    Button popupNo = (Button) popupView.findViewById(R.id.popupNo);

                    popupYes.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Log.v("popup: ", "yes button");

                            // rescriu rezultatul
                            winnersList.addUser(user, 0);
                            startGameButton.setEnabled(true);
                            popupWindow.dismiss();
                        }

                    });

                    popupNo.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Log.v("popup NO:", "popup");
                            popupWindow.dismiss();
                            userName.setText("");
                            startGameButton.setEnabled(false);

                            Toast.makeText(getApplicationContext(), "Enter a new username", Toast.LENGTH_LONG).show();
                        }

                    });

                } else if (!winnersList.contains(user)) {

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
                Intent intent = new Intent(MainActivity.this, WinnersActivity.class);
                startActivity(intent);
            }
        });
    }
}
