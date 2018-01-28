package ainhoamoreno.com.lastfm.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.artist.ui.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.data.artist.search.Artist;
import ainhoamoreno.com.lastfm.data.artist.search.ImageType;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnArtistClickListener {

    private SearchView mSearchView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.emptyListView) TextView mEmptyView;

    @BindString(R.string.search_no_results) String mNoResults;

    ArtistRepository repository;
    SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        repository = new ArtistRepository(LastFmApplication.get().getService());

        setUpRecyclerView(R.id.mediumRb);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSearchView != null) {
            mSearchView.clearFocus();
        }
    }

    private void setUpRecyclerView(@IdRes int viewId) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(null);

        final @ImageType.Type String type;
        switch (viewId) {
            case R.id.smallRb:
                type = ImageType.SMALL;
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.mediumRb:
                type = ImageType.MEDIUM;
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.largeRb:
                type = ImageType.LARGE;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            default:
                type = ImageType.LARGE;
                break;
        }

        mAdapter = new SearchAdapter(type, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void search(final String artistName) {
        final List<Artist> list = new ArrayList<>();
        repository.getSearch(artistName)
                .doOnNext(list::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> setEmptyViewVisibility(list.isEmpty()))
                .subscribe(artist -> mAdapter.setData(list));
    }

    public void onRadioButtonClicked(final View view) {
        setUpRecyclerView(view.getId());

        search(mSearchView.getQuery().toString());
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
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        mSearchView.setQuery("cher", true);

        return true;
    }

    private void setEmptyViewVisibility(boolean showEmptyView) {
        if (showEmptyView) {
            mRecyclerView.setAdapter(null);
            mEmptyView.setText(mNoResults);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
    }
}
