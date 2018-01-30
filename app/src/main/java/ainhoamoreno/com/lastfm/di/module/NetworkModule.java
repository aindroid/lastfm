package ainhoamoreno.com.lastfm.di.module;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import ainhoamoreno.com.lastfm.di.qualifiers.Url;
import ainhoamoreno.com.lastfm.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @AppScope
    @Provides
    Retrofit provideRetrofit(@Url String url, GsonConverterFactory gsonConverterFactory,
                             OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    GsonConverterFactory provideGsonFactory() {
        Gson gson = new Gson();

        return GsonConverterFactory.create(gson);
    }

    @AppScope
    @Provides
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(interceptor)
                .build();
    }

    @AppScope
    @Provides
    Interceptor provideInterceptor() {
        return new LoggingInterceptor();
    }

    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);

            Log.d(NetworkModule.class.getSimpleName(), "response = " + response.message());
            return response;
        }
    }

}
