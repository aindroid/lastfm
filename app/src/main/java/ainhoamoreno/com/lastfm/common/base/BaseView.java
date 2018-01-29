package ainhoamoreno.com.lastfm.common.base;

import android.content.Context;
import android.support.annotation.Nullable;

public interface BaseView {

    Context getContext();

    void setUpToolbar(@Nullable final String title);
}
