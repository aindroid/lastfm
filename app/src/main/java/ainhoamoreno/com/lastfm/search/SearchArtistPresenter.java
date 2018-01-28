package ainhoamoreno.com.lastfm.search;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.artist.search.Artist;
import ainhoamoreno.com.lastfm.data.artist.search.ImageType;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ainhoa on 28/01/2018.
 */

public class SearchArtistPresenter {

    final SearchActivity activity;
    final ArtistRepository repository;
    SearchAdapter mAdapter;
    private final List<Artist> mResults = new ArrayList<>();
    private boolean mIsLoading;
    private String mArtistName;

    public SearchArtistPresenter(SearchActivity activity) {
        this.activity = activity;
        repository = new ArtistRepository(LastFmApplication.get().getService());
    }

    public void newSearch(final String artistName) {
        mResults.clear();

        search(artistName, 1);
    }

    private void search(final String artistName, int page) {
        Log.d(SearchArtistPresenter.class.getName(), "search() - page = " + page);

        mIsLoading = true;

        mArtistName = artistName;

        repository.getSearch(artistName, page)
                .doOnNext(mResults::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    activity.setEmptyViewVisibility(mResults.isEmpty());
                    mIsLoading = false;
                })
                .subscribe(artist -> mAdapter.setData(mResults));
    }

    public void setUpRecyclerView(@IdRes int viewId) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        activity.mRecyclerView.setHasFixedSize(true);

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

        mAdapter = new SearchAdapter(type, activity);
        activity.mRecyclerView.setAdapter(mAdapter);

    }

    class ArtistsScrollListener extends PaginationScrollListener {

//        private final String mArtistName;
        private int mPage = 1;

        public ArtistsScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);

//            mArtistName = artistName;
            mPage = 1;
        }

        @Override
        protected void loadMoreItems() {
            mPage ++;

            search(mArtistName, mPage);
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
