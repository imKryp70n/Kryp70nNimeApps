package com.kryp70nnime.apps.Activity.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kryp70nnime.apps.Activity.dashboard.Fragment.DashboardFragment;
import com.kryp70nnime.apps.Activity.dashboard.Fragment.JadwalFragment;
import com.kryp70nnime.apps.Activity.dashboard.Fragment.SettingFragment;
import com.kryp70nnime.apps.Activity.dashboard.Fragment.TrendingFragment;
import com.kryp70nnime.apps.R;

public class DashboardActivity extends AppCompatActivity  {



    private boolean isPopular = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragmentDashboard = new DashboardFragment();
        final Fragment fragmentTrending = new TrendingFragment();
        final Fragment fragmentJadwal = new JadwalFragment();
        final Fragment fragmentSetting = new SettingFragment();
        fragmentManager.beginTransaction().add(R.id.dashboardContainer,fragmentDashboard).commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.dashboardBottom:
                        fragment = fragmentDashboard;
                        break;
                    case R.id.trendingBottom:
                        fragment = fragmentTrending;
                        break;
                    case R.id.jadwalBottom:
                        fragment = fragmentJadwal;
                        break;
                    case R.id.pengaturanBottom:
                        fragment = fragmentSetting;
                        break;
                    default:fragment = fragmentDashboard;
                }
                fragmentManager.beginTransaction().replace(R.id.dashboardContainer,fragment).commit();

                return true;
            }
        });

    }
}