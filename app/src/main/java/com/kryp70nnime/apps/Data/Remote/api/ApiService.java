package com.kryp70nnime.apps.Data.Remote.api;

import com.kryp70nnime.apps.Data.Remote.models.AnimeEpisodeModels.AnimeEpisodeModel;
import com.kryp70nnime.apps.Data.Remote.models.InfoModels.InfoModel;
import com.kryp70nnime.apps.Data.Remote.models.RecentEpisodeModels.RecentEpisodeModel;
import com.kryp70nnime.apps.Data.Remote.models.SearchModels.SearchResponse;
import com.kryp70nnime.apps.Data.Remote.models.ServerAvailableModels.ServerAvailableModel;
import com.kryp70nnime.apps.Data.Remote.models.TopAiringModels.TopAiringModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("anime/gogoanime/{query}")
    Call<SearchResponse>
    getSearchAnime(@Path("query") String query);

    @GET("anime/gogoanime/info/{animeId}")
    Call<InfoModel>
    getAnimeInfo(@Path("animeId") String animeId);


    @GET("anime/gogoanime/top-airing")
    Call<TopAiringModel>
    getTopAiring();

    @GET("anime/gogoanime/recent-episodes")
    Call<RecentEpisodeModel>
    getRecentEpisodes();

    @GET("anime/gogoanime/watch/{episode-id}")
    Call<AnimeEpisodeModel>
    getAnimeEpisode(@Path("episode-id") String EpisodeID);

    @GET("anime/gogoanime/servers/{episode-id}")
    Call<List<ServerAvailableModel>>
    getServerAvailable();







}
