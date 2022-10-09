package com.kryp70nnime.apps.Data.PreferencesHelper;


import android.content.SharedPreferences;

import com.kryp70nnime.apps.Data.Remote.models.BookmarkModels.BookmarkModel;

public class PreferencesHelper implements Preferences{
    public static final String PREF_FILE_NAME = "kryp70nnime_pref_file";
    public static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    public static final String PREF_KEY_TOP_AIRING = "PREF_KEY_TOP_AIRING";

    private final SharedPreferences mPrefs;

    public PreferencesHelper(SharedPreferences mPrefs) {
        this.mPrefs = mPrefs;
    }


    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

}
