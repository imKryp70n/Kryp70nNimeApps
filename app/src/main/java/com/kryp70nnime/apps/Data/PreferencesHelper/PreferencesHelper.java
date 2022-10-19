package com.kryp70nnime.apps.Data.PreferencesHelper;


import android.content.Context;
import android.content.SharedPreferences;

import com.kryp70nnime.apps.Data.Remote.models.BookmarkModels.BookmarkModel;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

public class PreferencesHelper implements Preferences{
    public static final String PREF_FILE_NAME = "kryp70nnime_pref_file";
    public static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    public static final String PREF_KEY_TOP_AIRING = "PREF_KEY_TOP_AIRING";

    HawkBuilder builder;



    public void saveAccessToken(String accessToken) {
        Hawk.put(PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    @Override
    public String getAccessToken() {
        return null;
    }
}
