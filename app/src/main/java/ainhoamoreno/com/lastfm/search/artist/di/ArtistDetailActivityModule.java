package ainhoamoreno.com.lastfm.search.artist.di;

import ainhoamoreno.com.lastfm.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.search.artist.ArtistContract;
import ainhoamoreno.com.lastfm.search.artist.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.search.artist.ArtistDetailPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ArtistDetailActivityModule {

    @Provides
    ArtistContract.View provideView(ArtistDetailActivity activity){
        return activity;
    }

    @Provides
    ArtistContract.Presenter providesPresenter(ArtistContract.View view,
                                               LastFmArtistApiImpl repository) {
        return new ArtistDetailPresenter(view, repository);
    }

}
