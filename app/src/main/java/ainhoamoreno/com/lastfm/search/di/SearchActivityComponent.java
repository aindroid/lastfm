package ainhoamoreno.com.lastfm.search.di;

import ainhoamoreno.com.lastfm.di.scope.ActivityScope;
import ainhoamoreno.com.lastfm.search.SearchActivity;
import ainhoamoreno.com.lastfm.search.di.SearchActivityModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = SearchActivityModule.class)
public interface SearchActivityComponent extends AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> {}
}
