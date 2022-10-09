package com.kryp70nnime.apps.Activity.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kryp70nnime.apps.Activity.dashboard.DashboardActivity;
import com.kryp70nnime.apps.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        },2000);
    }
}