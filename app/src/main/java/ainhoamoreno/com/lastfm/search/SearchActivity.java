package ainhoamoreno.com.lastfm.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.artist.ui.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.data.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import ainhoamoreno.com.lastfm.util.Resources;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnArtistClickListener {

    @BindView(R.id.searchView) SearchView mSearchView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;

    ArtistRepository repository;
    SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

        setUpRecyclerView(new LinearLayoutManager(this));

        repository = new ArtistRepository(LastFmApplication.get().getService());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSearchView.clearFocus();
    }

    private void setUpRecyclerView(RecyclerView.LayoutManager layoutManager) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SearchAdapter(Resources.pxFromDp(this, 300), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void search(final String artistName) {
        final List<Artist> list = new ArrayList<>();
        repository.getSearch(artistName)
                .doOnNext(list::add)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> mAdapter.setData(list));
    }

    public void onRadioButtonClicked(View view) {
        String size = (String) view.getTag();
        float newSize = Resources.pxFromDp(this, Float.parseFloat(size));

        mRecyclerView.setAdapter(null);

        switch (view.getId()) {
            case R.id.smallRb:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.mediumRb:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.largeRb:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
        }

        mAdapter = new SearchAdapter(newSize, this);
        mRecyclerView.setAdapter(mAdapter);
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
}
