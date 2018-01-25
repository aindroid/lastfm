package ainhoamoreno.com.lastfm.di;

import javax.inject.Singleton;

import ainhoamoreno.com.lastfm.network.LastFmRetrofit;
import ainhoamoreno.com.lastfm.network.LastFmService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = NetworkModule.class)
public class LastFmModule {

    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    @Provides
    @Singleton
    public LastFmService provideWordService(LastFmRetrofit retrofit) {
        return retrofit.getRetrofit().create(LastFmService.class);
    }

    @Provides
    @Singleton
    public LastFmRetrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                                           OkHttpClient okHttpClient) {
        return new LastFmRetrofit(BASE_URL, gsonConverterFactory, okHttpClient);
    }
}
