package ainhoamoreno.com.lastfm.artistdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.data.api.LastFmArtistApiImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ArtistDetailPresenter implements ArtistDetailContract.Presenter {

    private final ArtistDetailContract.View mView;
    private final LastFmArtistApiImpl mRepository;

    private Disposable mDisposable;

    @Inject
    public ArtistDetailPresenter(ArtistDetailContract.View view, LastFmArtistApiImpl repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getArtistInfo(@NonNull final String mbid) {
        mDisposable = mRepository.getArtistInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> mView.updateArtistContent(artist.getContent()));
    }

    @Override
    public void loadImage(@NonNull final String imageUrl) {
        Picasso.with(mView.getContext())
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mView.updateArtistImg(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        mView.updateArtistImg(null);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    @Override
    public void unSubscribe() {
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
