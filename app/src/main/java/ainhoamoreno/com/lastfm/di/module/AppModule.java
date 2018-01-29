package ainhoamoreno.com.lastfm.di.module;

import android.app.Application;
import android.content.Context;

import ainhoamoreno.com.lastfm.artistdetail.di.ArtistDetailActivityComponent;
import ainhoamoreno.com.lastfm.di.scope.AppScope;
import ainhoamoreno.com.lastfm.search.di.SearchActivityComponent;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {SearchActivityComponent.class, ArtistDetailActivityComponent.class})
public class AppModule {

    @AppScope
    @Provides
    Context provideContext(Application application) {
        return application;
    }

}