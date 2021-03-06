package ainhoamoreno.com.lastfm.search.di;

import ainhoamoreno.com.lastfm.di.scope.ActivityScope;
import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import ainhoamoreno.com.lastfm.search.SearchActivity;
import ainhoamoreno.com.lastfm.search.SearchArtistPresenter;
import ainhoamoreno.com.lastfm.search.SearchContract;
import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @ActivityScope
    @Provides
    SearchContract.View provideView(SearchActivity activity) {
        return activity;
    }

    @ActivityScope
    @Provides
    SearchContract.Presenter providesPresenter(SearchContract.View searchView,
                                               ArtistDataProvider dataProvider) {
        return new SearchArtistPresenter(searchView, dataProvider);
    }

}
