package com.kryp70nnime.apps.PreferencesHelper;


import android.content.SharedPreferences;

public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "kryp70nnime_pref_file";
    public static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    public static final String PREF_KEY_TOP_AIRING = "PREF_KEY_TOP_AIRING";

    private final SharedPreferences mPrefs;

    public PreferencesHelper(SharedPreferences mPrefs) {
        this.mPrefs = mPrefs;
    }

    public void setPrefKeyTopAiring(String topAiring) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, topAiring).apply();
    }

    public String getPrefKeyTopAiring() {
        return mPrefs.getString(PREF_KEY_TOP_AIRING, null);
    }


}
