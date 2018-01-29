package ainhoamoreno.com.lastfm.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import ainhoamoreno.com.lastfm.search.artist.di.ArtistDetailActivityComponent;
import ainhoamoreno.com.lastfm.search.di.SearchActivityComponent;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {SearchActivityComponent.class, ArtistDetailActivityComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}