package ainhoamoreno.com.lastfm.artistdetail;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ArtistDetailPresenter implements ArtistDetailContract.Presenter {

    private final ArtistDetailContract.View mView;
    private final ArtistDataProvider mDataProvider;

    private Disposable mDisposable;

    @Inject
    public ArtistDetailPresenter(ArtistDetailContract.View view, ArtistDataProvider dataProvider) {
        mView = view;
        mDataProvider = dataProvider;
    }

    @Override
    public void getArtistInfo(@NonNull String mbid) {
        mDisposable = mDataProvider.getArtistInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> mView.updateArtistContent(artist.getContent()));
    }

    @Override
    public void unSubscribe() {
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
