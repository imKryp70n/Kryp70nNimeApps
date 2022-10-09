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
import com.kryp70nnime.apps.di.Interceptor.MainInterceptor;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.InfoModels.InfoModel;
import com.kryp70nnime.apps.Data.Remote.models.TopAiringModels.TopAiringModel;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {


    ApiService apiService;
    InfoModel infoModel;
    TopAiringModel topAiringModel;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_list, parent, false);
        return new ViewHolder((ViewGroup) view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(holder.itemView.getContext().getCacheDir(), cacheSize))
                .addInterceptor(chain -> {

                    Request request = chain.request();
                    //request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + Hour).build();
                    if (MainInterceptor.isInternetAvailable(holder.itemView.getContext())) {
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        //request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + Hour).build();
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                    }
                    return chain.proceed(request);
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImageView animeImage = holder.itemView.findViewById(R.id.animeImage);
        TextView txtTitle = holder.itemView.findViewById(R.id.animeTitle);
        TextView txtReleaseDate = holder.itemView.findViewById(R.id.releaseDateItem);
        TextView txtSubOrDub = holder.itemView.findViewById(R.id.subOrDubItem);
        TextView txtStatus = holder.itemView.findViewById(R.id.statusAnimeItem);
        CardView animeCard = holder.itemView.findViewById(R.id.AnimeCardView);
        ShimmerFrameLayout shimmerFrameLayout = holder.itemView.findViewById(R.id.shimmerAnime);


        // CHECK IF CACHE IS AVAILABLE THEN USE IT


        if (topAiringModel == null) {

            apiService = retrofit.create(ApiService.class);
            shimmerFrameLayout.startShimmer();
            apiService.getTopAiring().enqueue(new Callback<TopAiringModel>() {
                @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                @Override
                public void onResponse(Call<TopAiringModel> call, Response<TopAiringModel> response) {
                    if (response.isSuccessful()) {
                        topAiringModel = response.body();

                        apiService.getAnimeInfo(topAiringModel.getResults().get(position).getId())
                                .enqueue(new Callback<InfoModel>() {
                                    @Override
                                    public void onResponse(Call<InfoModel> call, Response<InfoModel> responseInfo) {
                                        if (responseInfo.isSuccessful()) {
                                            infoModel = responseInfo.body();
                                            Glide.with(holder.itemView.getContext())
                                                    .load(topAiringModel.getResults().get(position).getImage())
                                                    .into(animeImage);
                                            txtTitle.setText(topAiringModel.getResults().get(position).getTitle());
                                            txtReleaseDate.setText("Release Date : " + infoModel.getReleaseDate());
                                            txtSubOrDub.setText("Sub or Dub : " + infoModel.getSubOrDub());
                                            txtStatus.setText("Status : " + infoModel.getStatus());
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
