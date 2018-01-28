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

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.artist.ui.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.data.artist.search.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnArtistClickListener {

    private SearchView mSearchView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindDimen(R.dimen.small_img_size) float smallImgSize;
    @BindDimen(R.dimen.medium_img_size) float mediumImgSize;
    @BindDimen(R.dimen.large_img_size) float largeImgSize;

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

//        mSearchView.setQuery("cher", true);
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

        final float size;
        switch (viewId) {
            case R.id.smallRb:
                size = smallImgSize;
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.mediumRb:
                size = mediumImgSize;
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.largeRb:
                size = largeImgSize;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            default:
                size = largeImgSize;
                break;
        }

        mAdapter = new SearchAdapter(size, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void search(final String artistName) {
        final List<Artist> list = new ArrayList<>();
        repository.getSearch(artistName)
                .doOnNext(list::add)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (list.isEmpty()) {
                            list.add(new Artist());
                            mAdapter.setData(list);
                        }
                    }
                })
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
}
