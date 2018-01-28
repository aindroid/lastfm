package ainhoamoreno.com.lastfm.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import ainhoamoreno.com.lastfm.LastFmApplication;

/**
 * Created by ainhoa on 25/01/2018.
 */

public class Resources {

    public static String getString(int resId) {
        return LastFmApplication.get().getString(resId);
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
