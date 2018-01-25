package ainhoamoreno.com.lastfm.di.component;

import ainhoamoreno.com.lastfm.search.SearchActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SearchActivitySubcomponent extends AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> {}
}
