package com.example.a442projects_thisappslaps_co;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceSpecUtil {

    public static float getScreenWidthDp(Context context) {
        DisplayMetrics metrics = getScreenDisplayMetrics(context);

        return metrics.widthPixels / metrics.density;
    }

    private static DisplayMetrics getScreenDisplayMetrics(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics;
    }
}
