package ainhoamoreno.com.lastfm.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.model.artist.ImageType;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchActivity extends DaggerAppCompatActivity implements SearchContract.View {

    @Inject SearchContract.Presenter mPresenter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.emptyListView) TextView mEmptyView;
    @BindView(R.id.progressBarView) View mProgressBarView;

    @BindString(R.string.search_no_results) String mNoResults;

    private SearchView mSearchView;
    private SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setUpToolbar(null);

        mRadioGroup.check(R.id.mediumRb);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) ->
                mPresenter.changeImgSizeSelection(checkedId)
        );

        setUpRecyclerView(ImageType.MEDIUM);
    }

    @Override
    public void setUpToolbar(@Nullable String title) {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void setUpRecyclerView(@ImageType.Type String imgType) {
        // changes in content will not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(null);

        final LinearLayoutManager layoutManager;
        switch (imgType) {
            case ImageType.SMALL:
                layoutManager = new GridLayoutManager(this, 3);
                break;
            case ImageType.MEDIUM:
                layoutManager = new GridLayoutManager(this, 2);
                break;
            default:
                layoutManager = new LinearLayoutManager(this);
                break;
        }

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new SearchScrollListener(layoutManager, mPresenter));

        mAdapter = mPresenter.createAdapter(imgType);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateResults(@NonNull List<ArtistItem> results) {
        mAdapter.setData(results);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // prevents the keyboard from opening up
        if (mSearchView != null) {
            mSearchView.clearFocus();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_activity, menu);

        MenuItem actionSearch = menu.findItem( R.id.action_search);
        mSearchView = (SearchView) actionSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.search(query);
                mSearchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void showNoResultsViews() {
        mProgressBarView.setVisibility(View.GONE);
        mEmptyView.setText(mNoResults);
        mEmptyView.setVisibility(View.VISIBLE);

        mRecyclerView.setAdapter(null);
    }

    @Override
    public void showResultsViews() {
        mEmptyView.setVisibility(View.GONE);
        mProgressBarView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void openRecyclerViewItem(@NonNull Class<? extends Activity> intentClass,
                                     @NonNull Bundle extras,
                                     @NonNull View transitionView) {

        Intent intent = new Intent(this, intentClass);
        intent.putExtras(extras);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                transitionView,
                ViewCompat.getTransitionName(transitionView));

        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.unSubscribe();
    }
}
