package com.kryp70nnime.apps.Ui.Activity.dashboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.BookmarkFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.DashboardFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.SearchAnimeFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.SettingFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.TrendingFragment;
import com.kryp70nnime.apps.Di.Interceptor.MainInterceptor;
import com.kryp70nnime.apps.R;

import java.util.ArrayList;
import java.util.List;


public class DashboardActivity extends AppCompatActivity {


    private boolean isPopular = true;
    private ViewPager viewPager;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);
        SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        //fragmentManager.beginTransaction().add(R.id.dashboardContainer, fragmentDashboard).commit();

        // check if connection is available
        if (MainInterceptor.isInternetAvailable(this)) {

        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
            sweetAlertDialog.setTitleText("No Internet Connection");
            sweetAlertDialog.setContentText("Please check your internet connection");
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                }
            });
            sweetAlertDialog.show();
        }

        if (sharedPreferences.getInt("TermCondition", 0) == 0) {
            Dialog dialog = new Dialog(DashboardActivity.this);
            dialog.setContentView(R.layout.popup_welcome);
            dialog.getContext();
            dialog.setCancelable(false);

            Button btnAccept = dialog.findViewById(R.id.btn_Accept);
            CheckBox CBAccept = dialog.findViewById(R.id.checkbox_agree);
            TextView TermsCondition = dialog.findViewById(R.id.txtTermsCondition);
            TermsCondition.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DashboardActivity.this, TermsContitionActivity.class);
                            startActivity(intent);
                        }
                    }
            );

            CBAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CBAccept.isChecked()) {
                        btnAccept.setEnabled(true);
                    } else {
                        btnAccept.setEnabled(false);

                    }
                }
            });
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    editor.putInt("TermCondition", 1).apply();
                }
            });
            dialog.show();
        }

        viewPager = findViewById(R.id.viewPager);


/*        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottomNavbar);
        chipNavigationBar.setItemSelected(R.id.dashboardBottom, true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;

                switch (i) {
                    case R.id.dashboardBottom:
                        viewPager.setCurrentItem(0);
                        break;
   *//*                 case R.id.trendingBottom:
                        fragment = fragmentTrending;
                        break;*//*
                    case R.id.searchBottom:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.jadwalBottom:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.pengaturanBottom:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                }
//                fragmentManager.beginTransaction().replace(R.id.dashboardContainer,fragment).commit();

*//*                if (savedInstanceState == null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.dashboardContainer, fragment);
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }*//*
            }
        });*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboardBottom:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.searchBottom:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.jadwalBottom:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.pengaturanBottom:
                        viewPager.setCurrentItem(3);
                        break;
                }


                return true;
            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        final Fragment fragmentDashboard = new DashboardFragment();
        final Fragment fragmentTrending = new TrendingFragment();
        final Fragment fragmentJadwal = new BookmarkFragment();
        final Fragment fragmentSetting = new SettingFragment();
        final Fragment fragmentSearch = new SearchAnimeFragment();
        adapter.addFragment(fragmentDashboard);
        adapter.addFragment(fragmentSearch);
        adapter.addFragment(fragmentJadwal);
        adapter.addFragment(fragmentSetting);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText("Keluar Aplikasi");
        sweetAlertDialog.setContentText("Apakah anda yakin ingin keluar dari aplikasi?");
        sweetAlertDialog.setConfirmText("Ya");
        sweetAlertDialog.setCancelText("Tidak");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
            }
        });
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();

    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();



        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }





}