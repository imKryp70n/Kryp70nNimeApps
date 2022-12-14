package com.kryp70nnime.apps.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kryp70nnime.apps.Activity.Watch.WatchAnimeActivity;
import com.kryp70nnime.apps.Activity.detail.DetailAnimeActivity;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.api.ApiService;
import com.kryp70nnime.apps.api.Constant;
import com.kryp70nnime.apps.models.InfoModels.InfoModel;
import com.kryp70nnime.apps.models.TopAiringModels.TopAiringModel;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeEpsAdapter extends RecyclerView.Adapter<AnimeEpsAdapter.ViewHolder> {

    ApiService apiService ;
    int Eps;
    InfoModel infoModel;


    @NonNull
    @Override
    public AnimeEpsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode_anime, parent, false);

        return new AnimeEpsAdapter.ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeEpsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        TextView txtEpsAnime = holder.itemView.findViewById(R.id.txtAnimeEps);
        CardView animeEps = holder.itemView.findViewById(R.id.AnimeEpsCardView);
        ShimmerFrameLayout shimmerFrameLayout = holder.itemView.findViewById(R.id.shimmerEpisode);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // save data call api and not call api again


        if (infoModel == null) {
            shimmerFrameLayout.startShimmer();
            apiService = retrofit.create(ApiService.class);
            apiService.getAnimeInfo(Constant.AnimeID).enqueue(new Callback<InfoModel>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<InfoModel> call, Response<InfoModel> response) {
                    if (response.isSuccessful()) {
                        infoModel = response.body();
                        txtEpsAnime.setText("Episode " + String.valueOf(response.body().getEpisodes().get(position).getNumber()));

                        animeEps.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Constant.EpisodeID = response.body().getEpisodes().get(position).getId();
                                Intent intent = new Intent(holder.itemView.getContext(), WatchAnimeActivity.class);
                                holder.itemView.getContext().startActivity(intent);

                            }
                        });
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.hideShimmer();
                    } else {
                        System.out.println("ERROR CODE" + response.code());
                        System.out.println("LINK " + call.request().url());
                    }
                }


                @Override
                public void onFailure(Call<InfoModel> call, Throwable t) {

                }

            });
        } else {
            txtEpsAnime.setText("Episode " + String.valueOf(infoModel.getEpisodes().get(position).getNumber()));
            animeEps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.EpisodeID = infoModel.getEpisodes().get(position).getId();
                    Intent intent = new Intent(holder.itemView.getContext(), WatchAnimeActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });

            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.hideShimmer();
        }
        System.out.println("Episode " + Eps);
    }


    @Override
    public int getItemCount() {
        return Constant.EpisodeAnime;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
        }
    }
}
