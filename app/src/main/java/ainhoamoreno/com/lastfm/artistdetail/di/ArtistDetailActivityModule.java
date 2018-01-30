package ainhoamoreno.com.lastfm.artistdetail.di;

import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailContract;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailPresenter;
import ainhoamoreno.com.lastfm.di.scope.ActivityScope;
import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class ArtistDetailActivityModule {

    @ActivityScope
    @Provides
    ArtistDetailContract.View provideView(ArtistDetailActivity activity){
        return activity;
    }

    @ActivityScope
    @Provides
    ArtistDetailContract.Presenter providesPresenter(ArtistDetailContract.View view,
                                                     ArtistDataProvider dataProvider) {
        return new ArtistDetailPresenter(view, dataProvider);
    }

}
