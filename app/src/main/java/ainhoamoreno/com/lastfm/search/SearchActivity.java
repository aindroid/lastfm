package ainhoamoreno.com.lastfm.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.artist.ui.ArtistDetailActivity;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnArtistClickListener {

    private SearchView mSearchView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.emptyListView) TextView mEmptyView;
    @BindView(R.id.progressBarView) View mProgressBarView;

    @BindString(R.string.search_no_results) String mNoResults;

    SearchArtistPresenter searchArtistPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        searchArtistPresenter = new SearchArtistPresenter(this);

        searchArtistPresenter.setUpRecyclerView(R.id.mediumRb);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSearchView != null) {
            mSearchView.clearFocus();
        }
    }

    public void onRadioButtonClicked(final View view) {
        searchArtistPresenter.setUpRecyclerView(view.getId());

//        searchArtistPresenter.newSearch(mSearchView.getQuery().toString());
        searchArtistPresenter.newSearch("cher");
    }

    @Override
    public void onClick(int position, ArtistItem artistItem, ImageView imageView) {
        Intent intent = new Intent(this, ArtistDetailActivity.class);
        intent.putExtra("EXTRA_ARTIST_ITEM", artistItem);
        intent.putExtra("EXTRA_ARTIST_IMAGE_TRANSITION_NAME", ViewCompat.getTransitionName(imageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView));

        startActivity(intent, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search_activity, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        mSearchView = (SearchView) myActionMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchArtistPresenter.newSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchArtistPresenter.newSearch("cher");

        return true;
    }

    public void setEmptyViewVisibility(boolean showEmptyView) {
        if (showEmptyView) {
            mRecyclerView.setAdapter(null);

            mProgressBarView.setVisibility(View.GONE);

            mEmptyView.setText(mNoResults);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
            mProgressBarView.setVisibility(View.VISIBLE);
        }
    }
}
