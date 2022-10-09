package com.kryp70nnime.apps.Ui.Activity.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.BookmarkFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.DashboardFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.SearchAnimeFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.SettingFragment;
import com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment.TrendingFragment;
import com.kryp70nnime.apps.di.Interceptor.MainInterceptor;
import com.kryp70nnime.apps.R;


public class DashboardActivity extends AppCompatActivity {


    private boolean isPopular = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);
        SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment fragmentDashboard = new DashboardFragment();
        final Fragment fragmentTrending = new TrendingFragment();
        final Fragment fragmentJadwal = new BookmarkFragment();
        final Fragment fragmentSetting = new SettingFragment();
        final Fragment fragmentSearch = new SearchAnimeFragment();
        fragmentManager.beginTransaction().add(R.id.dashboardContainer, fragmentDashboard).commit();

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

        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottomNavbar);
        chipNavigationBar.setItemSelected(R.id.dashboardBottom, true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;

                switch (i) {
                    case R.id.dashboardBottom:
                        fragment = fragmentDashboard;
                        break;
                    case R.id.trendingBottom:
                        fragment = fragmentTrending;
                        break;
                    case R.id.searchBottom:
                        fragment = fragmentSearch;
                        break;
                    case R.id.jadwalBottom:
                        fragment = fragmentJadwal;
                        break;
                    case R.id.pengaturanBottom:
                        fragment = fragmentSetting;
                        break;
                    default:
                        fragment = fragmentDashboard;
                }
//                fragmentManager.beginTransaction().replace(R.id.dashboardContainer,fragment).commit();

                if (savedInstanceState == null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    transaction.replace(R.id.dashboardContainer, fragment);
                    transaction.setReorderingAllowed(true);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


/*        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                return true;
            }
        });*/

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
}