package com.example.cata.bullsandcows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cata.bullsandcows.enums.IntentKey;
import com.example.cata.bullsandcows.enums.Logs;

import java.util.*;

public class StartGame extends AppCompatActivity {
    private String secret;
    private EditText numberSend;
    private TextView resultTextView;
    private Button sendNumberButton;
    private Button startAgainButton;
    private int counterRounds = 0;

    private Winners winnersList = Winners.getInstance();
    private String userName;

    private final static String winner = "WINNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        /* back button */
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        numberSend = (EditText) findViewById(R.id.numberSent);
        sendNumberButton = (Button) findViewById(R.id.send);
        startAgainButton = (Button) findViewById(R.id.startAgain);

        resultTextView = (TextView) findViewById(R.id.result);
        resultTextView.setMovementMethod(new ScrollingMovementMethod());

        /* extract the secret number and the userName */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            secret = extras.getString(IntentKey.SECRETNUMBER.toString());
            userName = extras.getString(IntentKey.USERNAME.toString());
        }

        sendNumberButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateInput();
            }
        });

        /* action on keyboard send button */
        numberSend.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    validateInput();
                    handled = true;
                }
                return handled;
            }
        });

        /* press start again button and generate a new secret number */
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random rand = new Random();
                int secretNumber = rand.nextInt(10000) + 1;

                Intent intent = new Intent(StartGame.this, StartGame.class);
                intent.putExtra(IntentKey.SECRETNUMBER.toString(), String.valueOf(secretNumber));
                intent.putExtra(IntentKey.USERNAME.toString(), userName);

                finish();
                startActivity(intent);
            }
        });

    }

    /***
     * calculate the number of bulls and cows
     * @param secret
     * @param guess
     * @return
     */
    private String getHint(String secret, String guess) {

        if (secret.length() > guess.length())
            return "too few digits";

        if (secret.length() < guess.length())
            return "too many digits";

        int countBull = 0;
        int countCow = 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        //check bulls
        for (int i = 0; i < secret.length(); i++) {
            char characterSecret = secret.charAt(i);

            if (characterSecret == guess.charAt(i)) {
                countBull++;
            } else {
                if (map.containsKey(characterSecret)) {
                    int freq = map.get(characterSecret);
                    freq++;
                    map.put(characterSecret, freq);
                } else {
                    map.put(characterSecret, 1);
                }
            }
        }

        //check cows
        for (int i = 0; i < secret.length(); i++) {
            char characterGuess = guess.charAt(i);

            if (secret.charAt(i) != characterGuess) {
                if (map.containsKey(characterGuess)) {

                    countCow++;

                    if (map.get(characterGuess) == 1) {
                        map.remove(characterGuess);
                    } else {
                        int freq = map.get(characterGuess);
                        freq--;
                        map.put(characterGuess, freq);
                    }
                }
            }
        }

        String result = "";
        if (countBull == secret.length())
            result = winner;
        else
            result = countBull + "A" + countCow + "B";

        return result;
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

    /***
     * validate the input
     */
    private void validateInput() {
        String guess = numberSend.getText().toString();
        if (guess.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid numaber", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.v("from edit text:", guess);
        Log.v("secret number:", secret);

        /* increase the number of rounds */
        counterRounds++;

        Log.v(Logs.COUNTER.toString(), String.valueOf(counterRounds));

        /* calculate and generate the result */
        String result = getHint(secret, guess);
        String sendText = resultTextView.getText() + "\n" + result + " number: " + guess;
        resultTextView.setText(sendText);

        numberSend.setText("");

        if (sendText.contains(winner)) {

            winnersList.addValue(userName, counterRounds);

            /* show start again button and hide start button */
            sendNumberButton.setEnabled(false);
            sendNumberButton.setVisibility(View.INVISIBLE);
            startAgainButton.setVisibility(View.VISIBLE);
        }
    }
}
