package com.kryp70nnime.apps.Ui.adapters.anime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kryp70nnime.apps.Ui.Activity.detail.DetailAnimeActivity;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.ResultsItem;
import com.kryp70nnime.apps.Data.Remote.models.TopAiringModels.TopAiringModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AnimeSearchResultAdapter extends RecyclerView.Adapter<AnimeSearchResultAdapter.ViewHolder> {
    ApiService apiService;
    TopAiringModel topAiringModel;
    List<ResultsItem> resultsItem;


    public void setAnimeResult(List<ResultsItem> resultsItems) {
        this.resultsItem = resultsItems;
    }

    @NonNull
    @Override
    public AnimeSearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_list, parent, false);
        return new ViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeSearchResultAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LinearLayout linearLayout = holder.itemView.findViewById(R.id.emptySearch);
        //Log.d("GET TITLE" , resultsItem.get(position).getTitle());

        if (resultsItem != null) {
            if (resultsItem.size() > 0) {
                ImageView animeImage = holder.itemView.findViewById(R.id.animeImage);
                TextView txtTitle = holder.itemView.findViewById(R.id.animeTitle);
                TextView txtReleaseDate = holder.itemView.findViewById(R.id.releaseDateItem);
                TextView txtSubOrDub = holder.itemView.findViewById(R.id.subOrDubItem);
                TextView txtStatus = holder.itemView.findViewById(R.id.statusAnimeItem);
                CardView animeCard = holder.itemView.findViewById(R.id.AnimeCardView);
                RecyclerView animeRecycler = holder.itemView.findViewById(R.id.animeSearchRecycler);
                ShimmerFrameLayout shimmerFrameLayout = holder.itemView.findViewById(R.id.shimmerAnime);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                shimmerFrameLayout.startShimmer();
                Glide.with(holder.itemView.getContext())
                        .load(resultsItem.get(position).getImage())
                        .into(animeImage);
                txtTitle.setText(resultsItem.get(position).getTitle());
                txtReleaseDate.setText(resultsItem.get(position).getReleaseDate());
                txtStatus.setVisibility(View.GONE);
                txtSubOrDub.setText("Sub or Dub : " + resultsItem.get(position).getSubOrDub());

                animeCard.setOnClickListener(view -> {
                    Intent intent = new Intent(view.getContext(), DetailAnimeActivity.class);
                    intent.putExtra("animeId", resultsItem.get(position).getId());
                    view.getContext().startActivity(intent);
                });
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.hideShimmer();

            } else {
                linearLayout.setVisibility(View.VISIBLE);
            }
        } else {

        }

    }


    @Override
    public int getItemCount() {
        if (resultsItem != null) {
            return resultsItem.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
        }
    }
}
