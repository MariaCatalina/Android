package com.devacademy.cata.facebooklogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.toString();
    CallbackManager callbackManager;

    LoginManager loginManager;
    AccessTokenTracker accessTokenTracker;

    private ImageView imageIcon;
    private TextView userName;

    private GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;

    private static final String TWITTER_KEY = "wW0pZ426EQEOp80bd9ouxnUhn";
    private static final String TWITTER_SECRET = "tmZbmiPEvCESsJk3AWBe7jv6BtXBu3I5Q9kJx693cgK0GmDenT";

    private TwitterLoginButton loginTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        /* initialization facebook login and handle logout */
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_facebook);

        userName = (TextView) findViewById(R.id.userName);
        imageIcon = (ImageView) findViewById(R.id.image_icon);

        loginManager = LoginManager.getInstance();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        final Profile profile = Profile.getCurrentProfile();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    //write your code here what to do when user logout
                    userName.setText("Guess");
                    imageIcon.setImageResource(R.drawable.user);

                    Log.i(TAG, "onCurrentAccessTokenChanged: logout");
                } else {
                    Log.i(TAG, "onCurrentAccessTokenChanged: login");

                    if (Profile.getCurrentProfile() != null)
                        updateUI((Profile.getCurrentProfile().getFirstName() + "\n" + Profile.getCurrentProfile().getLastName()), profile.getProfilePictureUri(100, 100));
                    else {
                        updateUI("Guess", null);
                    }
                }
            }
        };

        /* configuration google login */

        // Configure sign-in to request the user's ID, email address, and basic profile. ID and
        // basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.i(TAG, "onConnectionFailed: failed");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);

        /* twitter login */
        loginTwitter = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                updateUI(session.getUserName(), null);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    /***
     * populate fields with user name and image
     *
     * @param userText
     * @param iconUri
     */
    public void updateUI(final String userText, final Uri iconUri) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userName.setText(userText);

                if (iconUri != null)
                    Picasso.with(MainActivity.this).load(iconUri).into(imageIcon);
                else
                    imageIcon.setImageResource(R.drawable.user);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult: " + requestCode + " " + data.toString());

        /* handle google response */
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                updateUI(acct.getDisplayName(), acct.getPhotoUrl());
                Log.i(TAG, "onActivityResult: " + acct.getPhotoUrl() + " , " + acct.getEmail());
            } else {
                Log.i(TAG, "onActivityResult: error login " + result.getStatus());
            }
        }

        loginTwitter.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI("Guess", null);
                    }
                });
    }

}
