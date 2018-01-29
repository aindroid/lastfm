package ainhoamoreno.com.lastfm;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import ainhoamoreno.com.lastfm.di.ActivityBuilderModule;
import ainhoamoreno.com.lastfm.di.AppModule;
import ainhoamoreno.com.lastfm.di.LastFmModule;
import ainhoamoreno.com.lastfm.network.LastFmArtistApi;
import dagger.BindsInstance;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

public class LastFmApplication extends DaggerApplication {

    @Inject
    LastFmArtistApi service;

    private static LastFmApplication sApplication;

    @dagger.Component(
            modules = {
                    AndroidSupportInjectionModule.class,
                    ActivityBuilderModule.class,
                    LastFmModule.class,
                    AppModule.class
            }
    )

    @Singleton
    interface Component extends AndroidInjector<LastFmApplication> {
        @dagger.Component.Builder
        abstract class Builder extends AndroidInjector.Builder<LastFmApplication> {
            @BindsInstance
            abstract Builder application(Application application);
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerLastFmApplication_Component.builder()
                .application(this)
                .create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
    }

    public LastFmArtistApi getService() {
        return service;
    }

    public static  LastFmApplication get() {
        return sApplication;
    }
}
