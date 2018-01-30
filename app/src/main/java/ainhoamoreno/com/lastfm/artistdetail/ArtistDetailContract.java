package ainhoamoreno.com.lastfm.artistdetail;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ainhoamoreno.com.lastfm.common.BasePresenter;
import ainhoamoreno.com.lastfm.common.BaseView;

public interface ArtistDetailContract {

    interface View extends BaseView {

        void updateArtistImg(@Nullable final Bitmap bitmap);

        void updateArtistContent(@Nullable final String content);
    }

    interface Presenter extends BasePresenter {

        void getArtistInfo(@NonNull final String mbid);

        void loadImage(@NonNull final String imageUrl);
    }
}
