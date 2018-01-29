package ainhoamoreno.com.lastfm.di.module;

import android.app.Activity;

import ainhoamoreno.com.lastfm.search.SearchActivity;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.artistdetail.di.ArtistDetailActivityComponent;
import ainhoamoreno.com.lastfm.search.di.SearchActivityComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module (subcomponents = SearchActivityComponent.class)
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivityInjectorFactory(SearchActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ArtistDetailActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivity(ArtistDetailActivityComponent.Builder builder);

}
