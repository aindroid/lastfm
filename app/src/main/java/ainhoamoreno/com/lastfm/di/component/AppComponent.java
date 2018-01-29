package ainhoamoreno.com.lastfm.di.component;

import android.app.Application;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.di.module.ActivityBuilderModule;
import ainhoamoreno.com.lastfm.di.module.AppModule;
import ainhoamoreno.com.lastfm.di.module.LastFmModule;
import ainhoamoreno.com.lastfm.di.scope.AppScope;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        LastFmModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<LastFmApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<LastFmApplication> {
        @BindsInstance
        public abstract AppComponent.Builder application(Application application);
    }
}
