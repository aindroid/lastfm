package ainhoamoreno.com.lastfm.di;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {FirebaseModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Application application) {
        Picasso picasso = new Picasso.Builder(application)
                .loggingEnabled(true)
                .build();

        Picasso.setSingletonInstance(picasso);
        return picasso;
    }

}