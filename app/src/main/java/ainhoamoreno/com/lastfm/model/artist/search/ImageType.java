package ainhoamoreno.com.lastfm.model.artist.search;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public interface ImageType {

    @Retention(SOURCE)
    @StringDef({SMALL, MEDIUM, LARGE, EXTRA_LARGE, MEGA})
    @interface Type {}
    String SMALL = "small";
    String MEDIUM = "medium";
    String LARGE = "large";
    String EXTRA_LARGE = "extralarge";
    String MEGA = "mega";

    @Retention(SOURCE)
    @IntDef({SMALL_INDEX, MEDIUM_INDEX, LARGE_INDEX})
    @interface Index {}
    int SMALL_INDEX = 0;
    int MEDIUM_INDEX = 1;
    int LARGE_INDEX = 2;
    int EXTRA_LARGE_INDEX = 3;
    int MEGA_INDEX = 4;
}
