package com.kryp70nnime.apps.Activity.Watch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.api.ApiService;
import com.kryp70nnime.apps.api.Constant;
import com.kryp70nnime.apps.models.AnimeEpisodeModels.AnimeEpisodeModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatchAnimeActivity extends AppCompatActivity {

    MediaSessionCompat mediaSessionCompat;
    PlaybackStateCompat.Builder builder;
    MediaControllerCompat mediaControllerCompat;
    PlayerView playerView;
    SimpleExoPlayer exoPlayer;
    int currentWindow = 0;
    long playbackPosition = 0;
    boolean playWhenReady = true;

    ApiService apiService;
    String EpisodeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_anime);
        WebView webView = findViewById(R.id.watchAnimeWeb);

/*        playerView = findViewById(R.id.amimeVideoPlayer);
        playerView.requestFocus();*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // get video url with okhttp


        apiService = retrofit.create(ApiService.class);

        apiService.getAnimeEpisode(Constant.EpisodeID).enqueue(new Callback<AnimeEpisodeModel>() {
            @Override
            public void onResponse(Call<AnimeEpisodeModel> call, Response<AnimeEpisodeModel> response) {
                if (response.code() == 200) {
                    AnimeEpisodeModel animeEpisodeModel = response.body();
                    if (animeEpisodeModel != null) {
                        // play hls video
                        try {
                            webView.setWebViewClient(new WebViewClient());
                            webView.getSettings().setJavaScriptEnabled(true);
                            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
                            webView.setWebChromeClient(new WebChromeClient());
                            webView.loadUrl(animeEpisodeModel.getSources().get(0).getUrl());
                        } catch (Exception e) {

                            System.out.println("ERROR : " + e.getMessage());
                        }
                    }


                } else {
                    AlertDialog dialog = new AlertDialog.Builder(getApplicationContext())
                            .setTitle("ERROR")
                            .setMessage(response.message())
                            .show();
                }

            }

            @Override
            public void onFailure(Call<AnimeEpisodeModel> call, Throwable t) {

            }
        });

    }

    private void initializePlayer(String Url) {
        playerView.setPlayer(exoPlayer);
        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentWindow, playbackPosition);
        MediaItem mediaItem = MediaItem.fromUri(Url);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();


    }

    private void releasePlayer() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }
}