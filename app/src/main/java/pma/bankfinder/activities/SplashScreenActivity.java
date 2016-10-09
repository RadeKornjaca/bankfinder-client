package pma.bankfinder.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;

import pma.bankfinder.R;
import pma.bankfinder.fragments.FirstTimeDialogFragment;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActiveAndroid.initialize(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String accessToken = sharedPreferences.getString(getString(R.string.settings_access_token), null);

        if(accessToken == null) {
            DialogFragment firstTimeDialogFragment = new FirstTimeDialogFragment();
            firstTimeDialogFragment.show(getSupportFragmentManager(), "first_time_running");
        } else {
            new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent startMainScreen = new Intent(getApplicationContext(), MapOverviewActivity.class);
                        startActivity(startMainScreen);
                        finish();
                    }
                }
            , DELAY_TIME);
        }


    }
}
