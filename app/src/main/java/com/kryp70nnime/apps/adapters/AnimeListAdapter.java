package com.kryp70nnime.apps.adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kryp70nnime.apps.Activity.detail.DetailAnimeActivity;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.api.ApiService;
import com.kryp70nnime.apps.api.Constant;
import com.kryp70nnime.apps.models.TopAiringModels.TopAiringModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {


ApiService apiService ;

    TopAiringModel topAiringModel;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_list, parent, false);
        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ImageView animeImage = holder.itemView.findViewById(R.id.animeImage);
        TextView txtTitle = holder.itemView.findViewById(R.id.animeTitle);
        TextView txtGenre = holder.itemView.findViewById(R.id.animeGenre);
        CardView animeCard = holder.itemView.findViewById(R.id.AnimeCardView);
        ShimmerFrameLayout shimmerFrameLayout = holder.itemView.findViewById(R.id.shimmerAnime);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (topAiringModel == null) {

            apiService = retrofit.create(ApiService.class);
            shimmerFrameLayout.startShimmer();
            apiService.getTopAiring().enqueue(new Callback<TopAiringModel>() {
                @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                @Override
                public void onResponse(Call<TopAiringModel> call, Response<TopAiringModel> response) {
                    if (response.isSuccessful()) {
                        topAiringModel = response.body();
                        Glide.with(holder.itemView.getContext())
                                .load(topAiringModel.getResults().get(position).getImage())
                                .into(animeImage);
                        txtTitle.setText(topAiringModel.getResults().get(position).getTitle());
                        txtGenre.setText("Genre : " + topAiringModel.getResults().get(position).getGenres());

                        animeCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(holder.itemView.getContext(), DetailAnimeActivity.class);
                                intent.putExtra("animeId", topAiringModel.getResults().get(position).getId());
                                holder.itemView.getContext().startActivity(intent);
                            }
                        });
                        shimmerFrameLayout.hideShimmer();
                        shimmerFrameLayout.stopShimmer();
                    } else {
                        System.out.println("ERROR CODE" + response.code());
                        System.out.println("LINK " + call.request().url());
                    }
                }

                @Override
                public void onFailure(Call<TopAiringModel> call, Throwable t) {

                }

            });
        } else {
            shimmerFrameLayout.hideShimmer();
            shimmerFrameLayout.stopShimmer();
            Glide.with(holder.itemView.getContext())
                    .load(topAiringModel.getResults().get(position).getImage())
                    .into(animeImage);
            txtTitle.setText(topAiringModel.getResults().get(position).getTitle());
            txtGenre.setText("Genre : " + topAiringModel.getResults().get(position).getGenres());

            animeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailAnimeActivity.class);
                    intent.putExtra("animeId", topAiringModel.getResults().get(position).getId());
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
        }
    }

}
