package ainhoamoreno.com.lastfm.di.module;

import ainhoamoreno.com.lastfm.BuildConfig;
import ainhoamoreno.com.lastfm.data.ArtistService;
import ainhoamoreno.com.lastfm.di.qualifiers.Url;
import ainhoamoreno.com.lastfm.di.scope.AppScope;
import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class LastFmModule {

    @Url
    @Provides
    String provideBaseUrl() {
        return BuildConfig.FLASH_FM_BASE_URL;
    }

    @AppScope
    @Provides
    ArtistService provideLastFmArtistApi(Retrofit retrofit) {
        return retrofit.create(ArtistService.class);
    }

    @AppScope
    @Provides
    ArtistDataProvider provideLastFmArtistApiImpl(ArtistService artistService) {
        return new ArtistDataProvider(artistService);
    }

}
