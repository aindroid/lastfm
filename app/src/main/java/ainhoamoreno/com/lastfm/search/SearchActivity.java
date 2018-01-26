package ainhoamoreno.com.lastfm.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import ainhoamoreno.com.lastfm.util.Resources;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchView) SearchView mSearchView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.radioGroup) RadioGroup mRadioGroup;

    ArtistRepository repository;
    SearchAdapter mAdapter;
    LinearLayoutManager linearLayoutManager;

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

        setUpRecyclerView(mRecyclerView);

        repository = new ArtistRepository(LastFmApplication.get().getService());
    }

    private void setUpRecyclerView(final RecyclerView recyclerView) {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SearchAdapter(Resources.pxFromDp(this, 300));
        recyclerView.setAdapter(mAdapter);
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
        mAdapter = new SearchAdapter(newSize);
        mRecyclerView.setAdapter(mAdapter);
        search(mSearchView.getQuery().toString());
    }
}
