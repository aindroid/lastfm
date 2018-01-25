package ainhoamoreno.com.lastfm.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        SearchAdapter mAdapter = new SearchAdapter();
        recyclerView.setAdapter(mAdapter);

        ArtistRepository repository = new ArtistRepository(LastFmApplication.get().getService());

        final List<Artist> list = new ArrayList<>();
        repository.getSearch("cher")
                .doOnNext(list::add)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> mAdapter.setData(list));

    }

}
