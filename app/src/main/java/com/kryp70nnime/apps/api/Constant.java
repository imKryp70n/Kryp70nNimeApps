package com.kryp70nnime.apps.api;

import android.content.SharedPreferences;

public class Constant {

    public static final String BASE_URL = "https://kryp70nnime.herokuapp.com/";
    public static String AnimeID ;
    public static final String SaveAnime = "KEY_SAVEANIME";
    public static int EpisodeAnime;
    public static String EpisodeID;
    public static final String AnimeList = "KEY_AnimeList";

    public String getAnimeList(){
        return AnimeList;
    }

}
