package ainhoamoreno.com.lastfm.artistdetail.di;

import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.di.scope.ActivityScope;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = ArtistDetailActivityModule.class)
public interface ArtistDetailActivityComponent extends AndroidInjector<ArtistDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ArtistDetailActivity> {}
}
