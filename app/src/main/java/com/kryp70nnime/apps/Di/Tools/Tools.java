package com.kryp70nnime.apps.Di.Tools;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.NonNull;

public class Tools {

    public static int dip2pix(@NonNull Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                context.getResources().getDisplayMetrics());
    }
}
