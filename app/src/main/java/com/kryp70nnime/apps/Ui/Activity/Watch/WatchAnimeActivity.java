package com.kryp70nnime.apps.Ui.Activity.Watch;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.kryp70nnime.apps.R;
import com.kryp70nnime.apps.Data.Remote.api.ApiService;
import com.kryp70nnime.apps.Data.Remote.api.Constant;
import com.kryp70nnime.apps.Data.Remote.models.AnimeEpisodeModels.AnimeEpisodeModel;
import com.kryp70nnime.apps.Di.TrackSelection.TrackSelectionDialog;

import net.webilisim.webplayer.WEBPlayerStd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WatchAnimeActivity extends AppCompatActivity {


    SimpleExoPlayer player;
    PlayerView playerView;
    WEBPlayerStd webPlayerStd;
    ApiService apiService;
    int EpsSelected;

    private DefaultTrackSelector trackSelector;
    private boolean isShowingTrackSelectionDialog;
    String[] speed = {"0.25x", "0.5x", "Normal", "1.5x", "2x"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_anime);
        trackSelector = new DefaultTrackSelector(this);
// ACCEPT ALL COOKIES
        CookieManager.getInstance().setAcceptCookie(true);
        playerView = findViewById(R.id.playerView);
        ImageView farwordBtn = playerView.findViewById(R.id.fwd);
        ImageView rewBtn = playerView.findViewById(R.id.rew);
        ImageView setting = playerView.findViewById(R.id.exo_track_selection_view);
        ImageView speedBtn = playerView.findViewById(R.id.exo_playback_speed);
        TextView speedTxt = playerView.findViewById(R.id.speed);


        webPlayerStd = findViewById(R.id.watchAnimePlayer);
        TextView animeTitle = findViewById(R.id.watchAnimeTitle);
        TextView animeEpsSelected = findViewById(R.id.watchAnimeEpisode);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        apiService = retrofit.create(ApiService.class);

        apiService.getAnimeEpisode(Constant.EpisodeID).enqueue(new Callback<AnimeEpisodeModel>() {
            @Override
            public void onResponse(Call<AnimeEpisodeModel> call, Response<AnimeEpisodeModel> response) {
                if (response.code() == 200) {
                    AnimeEpisodeModel animeEpisodeModel = response.body();
                    if (animeEpisodeModel != null) {
                        // play hls video
                        try {

                            playerView.setPlayer(player);

                            farwordBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    player.seekTo(player.getCurrentPosition() + 10000);
                                }
                            });
                            rewBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    long num = player.getCurrentPosition() - 10000;
                                    if (num < 0) {
                                        player.seekTo(0);
                                    } else {
                                        player.seekTo(player.getCurrentPosition() - 10000);
                                    }


                                }
                            });

                            ImageView fullscreenButton = playerView.findViewById(R.id.fullscreen);
                            fullscreenButton.setOnClickListener(new View.OnClickListener() {
                                @SuppressLint("SourceLockedOrientationActivity")
                                @Override
                                public void onClick(View view) {
                                    int orientation = WatchAnimeActivity.this.getResources().getConfiguration().orientation;
                                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                                        // code for portrait mode
                                        WatchAnimeActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                                        playerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                        fullscreenButton.setImageResource(R.drawable.ic_baseline_fullscreen_exit_24);

                                    } else {
                                        // code for landscape mode
                                        WatchAnimeActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                                        playerView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                                                getApplicationContext().getResources().getDisplayMetrics());

                                        fullscreenButton.setImageResource(R.drawable.ic_baseline_fullscreen_24);

                                    }


                                }
                            });


                            findViewById(R.id.exo_play).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    player.play();

                                }
                            });
                            findViewById(R.id.exo_pause).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    player.pause();

                                }
                            });


/*                            player.addListener(new Player.Listener() {
                                @Override
                                public void onPlaybackStateChanged(int state) {
                                    if (state == ExoPlayer.STATE_ENDED) {

                                    }

                                }
                            });*/


                            playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
                                @Override
                                public void onVisibilityChange(int visibility) {

                                }
                            });

                            setting.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(trackSelector)) {
                                        isShowingTrackSelectionDialog = true;
                                        TrackSelectionDialog trackSelectionDialog = TrackSelectionDialog.createForTrackSelector(trackSelector,
                                                        /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                                        trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);

                                    }
                                }
                            });


                            player = new SimpleExoPlayer.Builder(WatchAnimeActivity.this)
                                    .setTrackSelector(trackSelector)
                                    .build();


                            player.setPlayWhenReady(true);
                            player.setRepeatMode(Player.REPEAT_MODE_ALL);
                            playerView.setPlayer(player);
                            MediaSource videoSource = buildMediaSource(animeEpisodeModel.getSources().get(0).getUrl());
                            player.setMediaSource(videoSource);
                            player.prepare();
                            playerView.setKeepScreenOn(true);
                            pDialog.dismiss();
/*
                            webPlayerStd.setUp(animeEpisodeModel.getSources().get(1).getUrl(), Constant.AnimeName + " " + "Episode : " + Constant.AnimeEpsSelected);
                            webPlayerStd.setSaveEnabled(true);*/
                            Log.d("URL 1", "onResponse: " + animeEpisodeModel.getSources().get(0).getUrl());
                            Log.d("URL 2", "onResponse: " + animeEpisodeModel.getSources().get(1).getUrl());

                            animeTitle.setText(Constant.AnimeName);
                            EpsSelected = getIntent().getIntExtra("EpsSelected", 0);
                            animeEpsSelected.setText("Episode : " + EpsSelected);
                        } catch (Exception e) {

                            System.out.println("ERROR : " + e.getMessage());
                        }
                    }


                } else {
                    AlertDialog dialog = new AlertDialog.Builder(WatchAnimeActivity.this)
                            .setTitle("ERROR")
                            .setMessage(response.message() + " Please report this error to developer")
                            .setPositiveButton("Report", (dialog1, which) -> {
                                // open web browser and report this error

                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    pDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<AnimeEpisodeModel> call, Throwable t) {

            }
        });

    }


    private MediaSource buildMediaSource(String url) {
        if (url.contains("m3u8")) {
            return new HlsMediaSource.Factory(new DefaultDataSourceFactory(this, Constant.USER_AGENT)).createMediaSource(Uri.parse(url));
        } else {
            return null;
        }

    }

    @Override
    public void onBackPressed() {
        if (playerView.getResizeMode() == AspectRatioFrameLayout.RESIZE_MODE_FILL) {

            WatchAnimeActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
            playerView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                    getApplicationContext().getResources().getDisplayMetrics());
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //WEBPlayer.releaseAllVideos();
    }


    protected void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            trackSelector = null;
        }

    }


    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}