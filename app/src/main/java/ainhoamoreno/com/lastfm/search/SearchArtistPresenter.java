package ainhoamoreno.com.lastfm.search;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.artistdetail.constants.Extras;
import ainhoamoreno.com.lastfm.data.model.artist.ImageType;
import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import ainhoamoreno.com.lastfm.utils.Strings;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SearchArtistPresenter implements SearchContract.Presenter, SearchAdapter.OnArtistClickListener {

    private static final String TAG = SearchArtistPresenter.class.getSimpleName();

    private static final int INITIAL_PAGE = 1;

    private final SearchContract.View mView;
    private final ArtistDataProvider mDataProvider;

    private List<ArtistItem> mResults = new ArrayList<>();
    private boolean mIsLoading;
    private String mArtistQuery;
    private int mPage;

    private Disposable mDisposable;

    @Inject
    public SearchArtistPresenter(SearchContract.View view, ArtistDataProvider dataProvider) {
        mView = view;
        mDataProvider = dataProvider;
    }

    @Override
    public void onClick(int position, ArtistItem artistItem, ImageView imageView) {
        Bundle extras = new Bundle();
        extras.putString(Extras.EXTRA_ARTIST_NAME, artistItem.getName());
        extras.putString(Extras.EXTRA_ARTIST_IMG_URL, artistItem.getImageUrl());
        extras.putString(Extras.EXTRA_ARTIST_MBID, artistItem.getMbid());
        extras.putString(Extras.EXTRA_ARTIST_IMAGE_TRANSITION_NAME,
                ViewCompat.getTransitionName(imageView));

        mView.openRecyclerViewItem(ArtistDetailActivity.class, extras, imageView);
    }

    @Override
    public SearchAdapter createAdapter(@ImageType.Type String imgType) {
        return new SearchAdapter(imgType, this);
    }

    @Override
    public void onImgSizeSelectionChanged(@IdRes int viewId) {

        final @ImageType.Type String imgType;
        switch (viewId) {
            case R.id.smallRb:
                imgType = ImageType.SMALL;
                break;
            case R.id.mediumRb:
                imgType = ImageType.MEDIUM;
                break;
            default:
                imgType = ImageType.LARGE;
                break;
        }

        Log.d(TAG, "Image size selection changed to " + imgType);

        changeImgSizeSelection(imgType);
    }

    @Override
    public void search(@NonNull String artistName) {
        mArtistQuery = artistName;
        mPage = INITIAL_PAGE;

        mResults.clear();

        loadNextPage();
    }

    @Override
    public void loadNextPage() {
        Log.d(TAG, "loadNextPage() - " + mArtistQuery + " - page = " + mPage);

        mView.showLoading();

        mIsLoading = true;

        mDisposable = mDataProvider.getArtists(mArtistQuery, mPage)
                .doOnNext(mResults::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {

                    if (mResults.isEmpty()) {
                        mView.showNoResultsViews();
                    } else {
                        mView.showResultsViews();
                    }

                    mPage ++;

                    mIsLoading = false;
                })
                .subscribe(artist -> mView.updateResults(mResults));
    }

    private void changeImgSizeSelection(@ImageType.Type String imgType) {
        mView.setUpRecyclerView(imgType);

        if (!Strings.isEmpty(mArtistQuery)) {
            search(mArtistQuery);
        }
    }

    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void unSubscribe() {
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @VisibleForTesting
    public void setResults(List<ArtistItem> list) {
        mResults = list;
    }

    @VisibleForTesting
    public void setArtistQuery(String artistQuery) {
        this.mArtistQuery = artistQuery;
    }
}
