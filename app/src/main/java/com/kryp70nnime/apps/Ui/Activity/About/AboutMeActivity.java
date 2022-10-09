package com.kryp70nnime.apps.Ui.Activity.About;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kryp70nnime.apps.Data.PreferencesHelper.PreferencesHelper;
import com.kryp70nnime.apps.R;

public class AboutMeActivity extends AppCompatActivity {


    PreferencesHelper preferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

    }
}