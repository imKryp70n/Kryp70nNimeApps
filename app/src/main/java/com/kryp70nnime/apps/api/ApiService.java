package com.kryp70nnime.apps.api;

import com.kryp70nnime.apps.models.AnimeEpisodeModels.AnimeEpisodeModel;
import com.kryp70nnime.apps.models.InfoModels.InfoModel;
import com.kryp70nnime.apps.models.RecentEpisodeModels.RecentEpisodeModel;
import com.kryp70nnime.apps.models.SearchModels.SearchResponse;
import com.kryp70nnime.apps.models.ServerAvailableModels.ServerAvailableModel;
import com.kryp70nnime.apps.models.TopAiringModels.TopAiringModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiService {
    @GET("anime/gogoanime/{query}")
    Call<List<SearchResponse>>
    getSearchAnime();

    @GET("anime/gogoanime/info/{animeId}")
    Call<InfoModel>
    getAnimeInfo(@Path("animeId") String animeId);


    @GET("anime/gogoanime/top-airing")
    Call<TopAiringModel>
    getTopAiring();

    @GET("anime/gogoanime/recent-episodes")
    Call<List<RecentEpisodeModel>>
    getRecentEpisodes();

    @GET("anime/gogoanime/watch/{episode-id}")
    Call<AnimeEpisodeModel>
    getAnimeEpisode(@Path("episode-id") String EpisodeID);

    @GET("anime/gogoanime/servers/{episode-id}")
    Call<List<ServerAvailableModel>>
    getServerAvailable();







}
