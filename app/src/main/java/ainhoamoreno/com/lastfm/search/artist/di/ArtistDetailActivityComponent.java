package ainhoamoreno.com.lastfm.search.artist.di;

import ainhoamoreno.com.lastfm.search.artist.ArtistDetailActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = ArtistDetailActivityModule.class)
public interface ArtistDetailActivityComponent extends AndroidInjector<ArtistDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ArtistDetailActivity> {}
}
