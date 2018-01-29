package ainhoamoreno.com.lastfm.search.artist;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.model.artist.getInfo.Bio;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArtistDetailPresenter implements ArtistContract.Presenter {

    private final ArtistContract.View mArtistView;
    private final LastFmArtistApiImpl mRepository;

    @Inject
    public ArtistDetailPresenter(ArtistContract.View artistView, LastFmArtistApiImpl repository) {
        mArtistView = artistView;
        mRepository = repository;
    }

    @Override
    public void getArtistInfo(@NonNull final String mbid) {
        mRepository.getInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> {
                    Bio bio = artist.bio;
                    if (!TextUtils.isEmpty(bio.content)) {
                        mArtistView.updateArtistContent(bio.content);
                    } else if (TextUtils.isEmpty(bio.summary)) {
                        mArtistView.updateArtistContent(bio.summary);
                    }
                });
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
