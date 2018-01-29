package ainhoamoreno.com.lastfm.di;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    GsonConverterFactory provideGsonFactory() {
        Gson gson = new Gson();

        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
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
