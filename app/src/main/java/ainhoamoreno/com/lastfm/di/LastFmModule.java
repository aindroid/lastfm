package ainhoamoreno.com.lastfm.di;

import javax.inject.Singleton;

import ainhoamoreno.com.lastfm.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.network.LastFmArtistApi;
import ainhoamoreno.com.lastfm.network.LastFmRetrofit;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class LastFmModule {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    @Provides
    @Singleton
    LastFmRetrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                                          OkHttpClient okHttpClient) {
        return new LastFmRetrofit(BASE_URL, gsonConverterFactory, okHttpClient);
    }

    @Provides
    @Singleton
    LastFmArtistApi provideLastFmArtistApi(LastFmRetrofit retrofit) {
        return retrofit.getRetrofit().create(LastFmArtistApi.class);
    }

    @Provides
    @Singleton
    LastFmArtistApiImpl provideLastFmArtistApiImpl(LastFmArtistApi lastFmArtistApi) {
        return new LastFmArtistApiImpl(lastFmArtistApi);
    }

}
