package ainhoamoreno.com.lastfm;

import ainhoamoreno.com.lastfm.di.component.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class LastFmApplication extends DaggerApplication {

    private static LastFmApplication sApplication;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
    }

    public static  LastFmApplication get() {
        return sApplication;
    }
}
