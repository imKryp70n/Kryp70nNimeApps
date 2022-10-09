package com.kryp70nnime.apps.Ui.Activity.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.kryp70nnime.apps.Ui.Activity.dashboard.DashboardActivity;
import com.kryp70nnime.apps.BuildConfig;
import com.kryp70nnime.apps.R;

public class Splash extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView version = findViewById(R.id.splashVersion);
        version.setText("Version " + BuildConfig.VERSION_NAME);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        int darkMode = sharedPreferences.getInt("NightMode", 0);
        if (darkMode == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            }, 4000);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            }, 4000);
        }
/*
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        },4000);*/
    }
}