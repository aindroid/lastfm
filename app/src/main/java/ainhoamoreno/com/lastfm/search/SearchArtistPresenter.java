package ainhoamoreno.com.lastfm.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.artistdetail.constants.Extras;
import ainhoamoreno.com.lastfm.common.listeners.PaginationScrollListener;
import ainhoamoreno.com.lastfm.data.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.data.model.artist.search.ImageType;
import ainhoamoreno.com.lastfm.ui.ArtistItem;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchArtistPresenter implements SearchContract.Presenter, SearchAdapter.OnArtistClickListener {

    private static final String TAG = SearchArtistPresenter.class.getSimpleName();

    private final List<ArtistItem> mResults = new ArrayList<>();
    private final SearchContract.View mSearchView;
    private final LastFmArtistApiImpl mRepository;

    private SearchAdapter mAdapter;
    private boolean mIsLoading;
    private String mArtistQuery;

    @Inject
    public SearchArtistPresenter(SearchContract.View searchView, LastFmArtistApiImpl repository) {
        mSearchView = searchView;
        mRepository = repository;
    }

    @Override
    public void onClick(int position, ArtistItem artistItem, ImageView imageView) {
        Bundle extras = new Bundle();
        extras.putString(Extras.EXTRA_ARTIST_NAME, artistItem.getName());
        extras.putString(Extras.EXTRA_ARTIST_IMG_URL, artistItem.getImageUrl());
        extras.putString(Extras.EXTRA_ARTIST_MBID, artistItem.getMbid());
        extras.putString(Extras.EXTRA_ARTIST_IMAGE_TRANSITION_NAME,
                ViewCompat.getTransitionName(imageView));

        mSearchView.openRecyclerViewItem(ArtistDetailActivity.class, extras, imageView);
    }

    @Override
    public void search(@NonNull String artistName) {
        mArtistQuery = artistName;

        mResults.clear();

        search(1);
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
    public void setUpRecyclerView(@ImageType.Type String imgType) {
        Context context = mSearchView.getContext();
        RecyclerView recyclerView = mSearchView.getRecyclerView();

        recyclerView.setAdapter(null);

        final LinearLayoutManager layoutManager;
        switch (imgType) {
            case ImageType.SMALL:
                layoutManager = new GridLayoutManager(context, 3);
                break;
            case ImageType.MEDIUM:
                layoutManager = new GridLayoutManager(context, 2);
                break;
            default:
                layoutManager = new LinearLayoutManager(context);
                break;
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new ArtistsScrollListener(layoutManager));

        mAdapter = new SearchAdapter(imgType, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void search(int page) {
        Log.d(TAG, "search() - " + mArtistQuery + " - page = " + page);

        mSearchView.showLoading();

        mIsLoading = true;

        mRepository.searchArtists(mArtistQuery, page)
                .doOnNext(mResults::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {

                    if (mResults.isEmpty()) {
                        mSearchView.showNoResults();
                    } else {
                        mSearchView.showResults();
                    }

                    mIsLoading = false;
                })
                .subscribe(artist -> mAdapter.setData(mResults));
    }

    private void changeImgSizeSelection(@ImageType.Type String imgType) {
        if (!TextUtils.isEmpty(mArtistQuery)) {
            setUpRecyclerView(imgType);

            search(mArtistQuery);
        }
    }

    class ArtistsScrollListener extends PaginationScrollListener {

        private final int mPage = 1;

        private ArtistsScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        protected void loadMoreItems() {
            int nextPage = mPage + 1;

            search(nextPage);
        }

        @Override
        public boolean isLastPage() {
            // todo this needs implementing
            return false;
        }

        @Override
        public boolean isLoading() {
            return mIsLoading;
        }
    }

}
