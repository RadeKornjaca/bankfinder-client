package pma.bankfinder.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pma.bankfinder.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startMainScreen = new Intent(getApplicationContext(), MapOverviewActivity.class);
                startActivity(startMainScreen);
                finish();
            }
        }, DELAY_TIME);


    }

}
