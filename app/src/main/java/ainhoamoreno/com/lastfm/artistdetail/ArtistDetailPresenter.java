package ainhoamoreno.com.lastfm.artistdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.data.api.LastFmArtistApiImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArtistDetailPresenter implements ArtistDetailContract.Presenter {

    private final ArtistDetailContract.View mArtistView;
    private final LastFmArtistApiImpl mRepository;

    @Inject
    public ArtistDetailPresenter(ArtistDetailContract.View artistView, LastFmArtistApiImpl repository) {
        mArtistView = artistView;
        mRepository = repository;
    }

    @Override
    public void getArtistInfo(@NonNull final String mbid) {
        mRepository.getArtistInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> mArtistView.updateArtistContent(artist.getContent()));
    }

    @Override
    public void loadImage(@NonNull final String imageUrl) {
        Picasso.with(mArtistView.getContext())
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mArtistView.updateArtistImg(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        mArtistView.updateArtistImg(null);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

}
