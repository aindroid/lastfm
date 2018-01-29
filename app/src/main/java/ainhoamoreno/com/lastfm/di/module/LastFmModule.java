package ainhoamoreno.com.lastfm.di.module;

import ainhoamoreno.com.lastfm.data.api.LastFmArtistApi;
import ainhoamoreno.com.lastfm.data.api.LastFmArtistApiImpl;
import ainhoamoreno.com.lastfm.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class LastFmModule {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    @AppScope
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                                   OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    LastFmArtistApi provideLastFmArtistApi(Retrofit retrofit) {
        return retrofit.create(LastFmArtistApi.class);
    }

    @AppScope
    @Provides
    LastFmArtistApiImpl provideLastFmArtistApiImpl(LastFmArtistApi lastFmArtistApi) {
        return new LastFmArtistApiImpl(lastFmArtistApi);
    }

}
