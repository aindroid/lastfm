package ainhoamoreno.com.lastfm.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity {

    ArtistRepository repository;
    SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SearchAdapter();
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
//        int size = (int) view.getTag();
    }
}
