package ainhoamoreno.com.lastfm.artistdetail;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ainhoamoreno.com.lastfm.common.BasePresenter;
import ainhoamoreno.com.lastfm.common.BaseView;

public interface ArtistDetailContract {

    interface View extends BaseView {

        void updateArtistImg(@Nullable Bitmap bitmap);

        void updateArtistContent(@Nullable String content);
    }

    interface Presenter extends BasePresenter {

        void getArtistInfo(@NonNull String mbid);
    }
}
