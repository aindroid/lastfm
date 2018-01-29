package ainhoamoreno.com.lastfm.search.di;

import ainhoamoreno.com.lastfm.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.search.SearchActivity;
import ainhoamoreno.com.lastfm.search.SearchArtistPresenter;
import ainhoamoreno.com.lastfm.search.SearchContract;
import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @Provides
    SearchContract.View provideView(SearchActivity activity){
        return activity;
    }

    @Provides
    SearchContract.Presenter providesPresenter(SearchContract.View searchView,
                                                               LastFmArtistApiImpl repository) {
        return new SearchArtistPresenter(searchView, repository);
    }

}
