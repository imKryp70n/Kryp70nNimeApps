package com.kryp70nnime.apps.Data.Remote.api;

import okhttp3.HttpUrl;

public class Constant {

    public static final String BASE_URL = "https://kryp70nnime.herokuapp.com/";
    public static final String DNS_URL = "https://security.cloudflare-dns.com/dns-query" ;
    public static String AnimeID;
    public static final String SaveAnime = "KEY_SAVEANIME";
    public static int EpisodeAnime;
    public static String EpisodeID;
    public static int AnimeEpsSelected;

    // Anime Information

    public static String AnimeName;
    public static int AnimeCount;
    public static int TotalEps;
    public static String StatusAnime;
    public static String ReleaseDate;
    public static String Description;

    // Search Anime

    public static String QuerySearch;

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36";
    public static final String AnimeList = "KEY_AnimeList";
    public static final String REFERER = "https://goload.pro/";
    public String getAnimeList() {
        return AnimeList;
    }

}
