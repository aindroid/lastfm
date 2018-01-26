package ainhoamoreno.com.lastfm.util;

import ainhoamoreno.com.lastfm.LastFmApplication;

/**
 * Created by ainhoa on 25/01/2018.
 */

public class Resources {

    public static String getString(int resId) {
        return LastFmApplication.get().getString(resId);
    }
}
