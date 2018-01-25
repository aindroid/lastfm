package ainhoamoreno.com.lastfm.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LastFmRetrofit {

    private final Retrofit retrofit;

    public LastFmRetrofit(String baseUrl,
                          GsonConverterFactory gsonConverterFactory,
                          OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
