package com.kryp70nnime.apps.di.Interceptor;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;

import java.io.IOException;

// OKHTTP UTILS
import okhttp3.Interceptor;
import okhttp3.Response;

public class MainInterceptor implements Interceptor {

    int cacheSize = 10 * 1024 * 1024; // 10 MB




    public static boolean isInternetAvailable(Context context) {

        boolean isConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected()) {
            isConnected = true;
        } else {
            isConnected = false;
        }


        /*
        // ping google.com
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            if (reachable) {
                isConnected = true;
            } else {
                isConnected = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

*/

        return isConnected;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return null;
    }
}