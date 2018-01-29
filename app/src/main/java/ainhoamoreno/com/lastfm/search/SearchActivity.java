package ainhoamoreno.com.lastfm.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.model.artist.search.ImageType;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class SearchActivity extends DaggerAppCompatActivity implements SearchContract.View {

    @Inject SearchContract.Presenter searchArtistPresenter;

    private SearchView mSearchView;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.emptyListView) TextView mEmptyView;
    @BindView(R.id.progressBarView) View mProgressBarView;

    @BindString(R.string.search_no_results) String mNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRadioGroup.check(R.id.mediumRb);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) ->
                searchArtistPresenter.onImgSizeSelectionChanged(checkedId)
        );

        // changes in content will not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        searchArtistPresenter.setUpRecyclerView(ImageType.MEDIUM);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
                searchArtistPresenter.search(query);
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
    public Context getContext() {
        return this;
    }

    @Override
    public void showNoResults() {
        mProgressBarView.setVisibility(View.GONE);
        mEmptyView.setText(mNoResults);
        mEmptyView.setVisibility(View.VISIBLE);

        mRecyclerView.setAdapter(null);
    }

    @Override
    public void showResults() {
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
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
