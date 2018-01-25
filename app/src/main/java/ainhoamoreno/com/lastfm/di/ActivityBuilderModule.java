package ainhoamoreno.com.lastfm.di;

import android.app.Activity;

import ainhoamoreno.com.lastfm.di.component.SearchActivitySubcomponent;
import ainhoamoreno.com.lastfm.search.SearchActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module (subcomponents = SearchActivitySubcomponent.class)
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivityInjectorFactory(SearchActivitySubcomponent.Builder builder);

}
