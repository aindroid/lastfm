package ainhoamoreno.com.lastfm.search;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.constants.Extras;
import ainhoamoreno.com.lastfm.artist.mapper.ArtistMapper;
import ainhoamoreno.com.lastfm.artist.ui.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.common.PaginationScrollListener;
import ainhoamoreno.com.lastfm.model.artist.search.Artist;
import ainhoamoreno.com.lastfm.model.artist.search.ImageType;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ainhoa on 28/01/2018.
 */

public class SearchArtistPresenter implements SearchContract.Presenter, SearchAdapter.OnArtistClickListener {

    final SearchActivity activity;
    final SearchContract.View mSearchView;

    final ArtistRepository repository;
    SearchAdapter mAdapter;
    private final List<Artist> mResults = new ArrayList<>();
    private boolean mIsLoading;
    private String mArtistName;

    public SearchArtistPresenter(SearchActivity activity, SearchContract.View searchView) {
        this.activity = activity;
        mSearchView = searchView;
        repository = new ArtistRepository(LastFmApplication.get().getService());
    }

    private void search(int page) {
        Log.d(SearchArtistPresenter.class.getName(), "search() - page = " + page);

        mSearchView.showLoading();

        mIsLoading = true;

        repository.getSearch(mArtistName, page)
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

    public void setUpRecyclerView(@IdRes int viewId) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        activity.mRecyclerView.setHasFixedSize(true);

        activity.mRecyclerView.setAdapter(null);

        final LinearLayoutManager layoutManager;
        final @ImageType.Type String type;
        switch (viewId) {
            case R.id.smallRb:
                type = ImageType.SMALL;
                layoutManager = new GridLayoutManager(activity, 3);
                break;
            case R.id.mediumRb:
                type = ImageType.MEDIUM;
                layoutManager = new GridLayoutManager(activity, 2);
                break;
            default:
                type = ImageType.LARGE;
                layoutManager = new LinearLayoutManager(activity);
                break;
        }

        activity.mRecyclerView.setLayoutManager(layoutManager);
        activity.mRecyclerView.addOnScrollListener(new ArtistsScrollListener(layoutManager));

        mAdapter = new SearchAdapter(type, this);
        activity.mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int position, ArtistMapper artistItem, ImageView imageView) {
        Intent intent = new Intent(activity, ArtistDetailActivity.class);
        intent.putExtra(Extras.EXTRA_ARTIST_ITEM, artistItem);
        intent.putExtra(Extras.EXTRA_ARTIST_IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(imageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                imageView,
                ViewCompat.getTransitionName(imageView));

        mSearchView.goToArtistDetailActivity(intent, options.toBundle());
    }

    @Override
    public void search(String artistName) {
        mArtistName = artistName;

        mResults.clear();

        search(1);
    }

    @Override
    public void onImgSizeSelectionChanged() {

    }

    class ArtistsScrollListener extends PaginationScrollListener {

        private int mPage;

        public ArtistsScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);

            mPage = 1;
        }

        @Override
        protected void loadMoreItems() {
            mPage ++;

            search(mPage);
        }

        @Override
        public boolean isLastPage() {
            return false;
        }

        @Override
        public boolean isLoading() {
            return mIsLoading;
        }
    }

}
