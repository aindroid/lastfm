package ainhoamoreno.com.lastfm.search;

import android.support.v7.widget.LinearLayoutManager;

import ainhoamoreno.com.lastfm.common.listeners.PaginationScrollListener;

public class SearchScrollListener extends PaginationScrollListener {

    private final SearchContract.Presenter mPresenter;

    SearchScrollListener(SearchContract.Presenter presenter, LinearLayoutManager layoutManager) {
        super(layoutManager);

        mPresenter = presenter;
    }

    @Override
    protected void loadMoreItems() {
        mPresenter.loadNextPage();
    }

    @Override
    public boolean isLastPage() {
        // todo this needs implementing
        return false;
    }

    @Override
    public boolean isLoading() {
        return mPresenter.isLoading();
    }
}