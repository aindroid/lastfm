package ainhoamoreno.com.lastfm.search.artist;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ainhoamoreno.com.lastfm.common.base.BasePresenter;
import ainhoamoreno.com.lastfm.common.base.BaseView;

public interface ArtistContract {

    interface View extends BaseView {

        void updateArtistImg(@Nullable final Bitmap bitmap);

        void updateArtistContent(@Nullable final String content);
    }

    interface Presenter extends BasePresenter {

        void getArtistInfo(@NonNull final String mbid);

        void loadImage(@NonNull final String imageUrl);
    }
}
