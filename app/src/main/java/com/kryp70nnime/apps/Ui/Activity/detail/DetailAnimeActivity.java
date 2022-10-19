package com.kryp70nnime.apps.Ui.Activity.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.tabs.TabLayout;
import com.kryp70nnime.apps.Ui.Activity.detail.fragment.EpisodesFragment;
import com.kryp70nnime.apps.Ui.Activity.detail.fragment.InformationFragment;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Ui.adapters.anime.InfoViewPagerAdapter;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.InfoModels.InfoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailAnimeActivity extends AppCompatActivity {

    ApiService apiService;
    InfoViewPagerAdapter infoViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anime);
        // ----------------------------- DEKLARASI -------------------------------

        TabLayout tabLayout = findViewById(R.id.tabLayoutDetail);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final InformationFragment informationFragment = new InformationFragment();
        final EpisodesFragment episodesFragment = new EpisodesFragment();

        // -----------------------------------------------------------------------

        fragmentManager.beginTransaction().add(R.id.animeDetailFrame,informationFragment).commit();

        ImageView animeImage = findViewById(R.id.animeImage);
        TextView txtTitle = findViewById(R.id.animeTitleDetail);
        ShimmerFrameLayout shimmerFrameLayout = findViewById(R.id.shimmerDescImage);
        ShimmerFrameLayout shimmerFrameLayoutTitle = findViewById(R.id.shimmerDescTitle);
        Button addToBookmark = findViewById(R.id.btn_favorite);

        String title = getIntent().getStringExtra("animeId");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        shimmerFrameLayout.startShimmer();
        shimmerFrameLayoutTitle.startShimmer();

        addToBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DetailAnimeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("Success");
                sweetAlertDialog.setContentText("Anime " + Constant.AnimeName + " has been added to your bookmark");
                sweetAlertDialog.show();

                                /*
                                editor.putString("animeId", Constant.AnimeID);
                                editor.putString("animeName", Constant.AnimeName);
                                editor.putString("URL", infoModel.getUrl());
                                editor.apply();*/

                // GET ALL DATA FROM SHARED PREFERENCES

            }
        });










        apiService = retrofit.create(ApiService.class);
        apiService.getAnimeInfo(title).enqueue(new Callback<InfoModel>() {
            @Override
            public void onResponse(Call<InfoModel> call, Response<InfoModel> response) {
                if (response.isSuccessful()){
                    InfoModel infoModel = response.body();
                    Glide.with(DetailAnimeActivity.this).load(infoModel.getImage()).into(animeImage);
                    txtTitle.setText(infoModel.getTitle());
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();
                    shimmerFrameLayoutTitle.stopShimmer();
                    shimmerFrameLayoutTitle.hideShimmer();

                } else {
                    System.out.println("ERROR CODE: " + response.code());
                    System.out.println("LINK" + call.request().url());
                }
            }

            @Override
            public void onFailure(Call<InfoModel> call, Throwable t) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    fragment = informationFragment;
                    fragmentManager.beginTransaction().replace(R.id.animeDetailFrame,fragment).commit();

                } else {
                    fragment = episodesFragment;
                    fragmentManager.beginTransaction().replace(R.id.animeDetailFrame,fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });




    }
}