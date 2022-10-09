package com.kryp70nnime.apps.Ui.adapters.anime;

import android.annotation.SuppressLint;
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
import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kryp70nnime.apps.Ui.Activity.detail.DetailAnimeActivity;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.InfoModels.InfoModel;
import com.kryp70nnime.apps.Data.Remote.models.RecentEpisodeModels.RecentEpisodeModel;
import com.kryp70nnime.apps.Data.Remote.models.TopAiringModels.TopAiringModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeRecentEpsAdapter extends RecyclerView.Adapter<AnimeRecentEpsAdapter.ViewHolder> {


    ApiService apiService;
    InfoModel infoModel;
    TopAiringModel topAiringModel;
    RecentEpisodeModel recentEpisodeModel;


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
        TextView txtReleaseDate = holder.itemView.findViewById(R.id.releaseDateItem);
        TextView txtSubOrDub = holder.itemView.findViewById(R.id.subOrDubItem);
        TextView txtStatus = holder.itemView.findViewById(R.id.statusAnimeItem);
        CardView animeCard = holder.itemView.findViewById(R.id.AnimeCardView);
        ShimmerFrameLayout shimmerFrameLayout = holder.itemView.findViewById(R.id.shimmerAnime);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (recentEpisodeModel == null) {

            apiService = retrofit.create(ApiService.class);
            shimmerFrameLayout.startShimmer();
            apiService.getRecentEpisodes().enqueue(new Callback<RecentEpisodeModel>() {
                @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                @Override
                public void onResponse(Call<RecentEpisodeModel> call, Response<RecentEpisodeModel> response) {
                    if (response.isSuccessful()) {
                        recentEpisodeModel = response.body();

                        apiService.getAnimeInfo(recentEpisodeModel.getResults().get(position).getId())
                                .enqueue(new Callback<InfoModel>() {
                                    @Override
                                    public void onResponse(Call<InfoModel> call, Response<InfoModel> responseInfo) {
                                        if (responseInfo.isSuccessful()) {
                                            infoModel = responseInfo.body();
                                            Glide.with(holder.itemView.getContext())
                                                    .load(recentEpisodeModel.getResults().get(position).getImage())
                                                    .into(animeImage);
                                            txtTitle.setText(recentEpisodeModel.getResults().get(position).getTitle());
                                            txtReleaseDate.setText("Release Date : " + infoModel.getReleaseDate());
                                            txtSubOrDub.setText("Sub or Dub : " + infoModel.getSubOrDub());
                                            txtStatus.setText("Status : " + infoModel.getStatus());
                                            animeCard.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(holder.itemView.getContext(), DetailAnimeActivity.class);
                                                    intent.putExtra("animeId", recentEpisodeModel.getResults().get(position).getId());
                                                    holder.itemView.getContext().startActivity(intent);
                                                }
                                            });
                                            shimmerFrameLayout.hideShimmer();
                                            shimmerFrameLayout.stopShimmer();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<InfoModel> call, Throwable t) {

                                    }
                                });


                    } else {
                        System.out.println("ERROR CODE" + response.code());
                        System.out.println("LINK " + call.request().url());
                    }
                }

                @Override
                public void onFailure(Call<RecentEpisodeModel> call, Throwable t) {

                }

            });
        } else {
            shimmerFrameLayout.hideShimmer();
            shimmerFrameLayout.stopShimmer();
            Glide.with(holder.itemView.getContext())
                    .load(recentEpisodeModel.getResults().get(position).getImage())
                    .into(animeImage);
            txtTitle.setText(recentEpisodeModel.getResults().get(position).getTitle());
            if (infoModel.getReleaseDate() != null && infoModel.getSubOrDub() != null && infoModel.getStatus() != null) {

                try {
                    txtReleaseDate.setText("Release Date : " + infoModel.getReleaseDate());
                    txtSubOrDub.setText("Sub or Dub : " + infoModel.getSubOrDub());
                    txtStatus.setText("Status : " + infoModel.getStatus());
                } catch (Exception e) {
                    e.printStackTrace();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(holder.itemView.getContext(), SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("ERROR");
                    sweetAlertDialog.setContentText("Something went wrong, " + e.getMessage());
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });
                    sweetAlertDialog.show();
                }
            } else {

            }
            animeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), DetailAnimeActivity.class);
                    intent.putExtra("animeId", recentEpisodeModel.getResults().get(position).getId());
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        if (recentEpisodeModel != null) {
            return recentEpisodeModel.getResults().size();
        } else {
            return 5;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
        }
    }

}
