package com.kryp70nnime.apps.Ui.Activity.detail.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.InfoModels.InfoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InformationFragment extends Fragment {


    ApiService apiService;
    String TitleAnime, Synopsis, Eps, ReleaseDate, Status;
    List<String> Genre = new ArrayList<>();
    InfoModel infoModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Bookmark", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView txtGenre = view.findViewById(R.id.animeGenreDetail);
        TextView txtSynopsis = view.findViewById(R.id.animeSynopsisDetail);
        TextView txtEps = view.findViewById(R.id.animeTotalEps);
        TextView txtReleaseDate = view.findViewById(R.id.releaseDateAnime);
        TextView txtStatus = view.findViewById(R.id.statusAnime);
        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shimmerDescInfo);
        String title = getActivity().getIntent().getStringExtra("animeId");
        Constant.AnimeID = title;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shimmerFrameLayout.startShimmer();

        // check if InfoModel is null
        if (infoModel == null) {
            apiService = retrofit.create(ApiService.class);
            apiService.getAnimeInfo(title).enqueue(new Callback<InfoModel>() {

                @Override
                public void onResponse(@NonNull Call<InfoModel> call, @NonNull Response<InfoModel> response) {
                    if (response.isSuccessful()) {
                        infoModel = response.body();
                        Genre = infoModel.getGenres();
                        Synopsis = infoModel.getDescription();
                        Eps = String.valueOf(infoModel.getTotalEpisodes());
                        ReleaseDate = infoModel.getReleaseDate();
                        Status = infoModel.getStatus();
                        Constant.AnimeName = infoModel.getTitle();
                        Constant.EpisodeAnime = infoModel.getTotalEpisodes();
                        Constant.TotalEps = infoModel.getTotalEpisodes();
                        Constant.StatusAnime = infoModel.getStatus();
                        Constant.Description = infoModel.getDescription();
                        Constant.ReleaseDate = infoModel.getReleaseDate();
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.hideShimmer();


                    } else {
                        System.out.println("ERROR CODE: " + response.code());
                        System.out.println("LINK" + call.request().url());
                    }
                    for (int i = 0; i < response.body().getGenres().size(); i++) {
                        // delete , in last genre
                        if (i == response.body().getGenres().size() - 1) {
                            txtGenre.append(Genre.get(i));
                        } else {
                            txtGenre.append(Genre.get(i) + ", ");
                        }
                    }

                    txtEps.setText(Eps + " Episodes");
                    txtReleaseDate.setText(ReleaseDate);
                    txtSynopsis.setText(Synopsis);
                    txtStatus.setText(Status);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.hideShimmer();


                }

                @Override
                public void onFailure(Call<InfoModel> call, Throwable t) {

                }
            });
        } else {
            for (int i = 0; i < infoModel.getGenres().size(); i++) {
                txtGenre.append(Genre.get(i) + ",");
            }
            txtEps.setText(Eps);
            txtReleaseDate.setText(ReleaseDate);
            txtSynopsis.setText(Synopsis);
            txtStatus.setText(Status);
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.hideShimmer();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }
}