package ainhoamoreno.com.lastfm.network;

import java.util.List;

import ainhoamoreno.com.lastfm.data.ArtistSearch;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LastFmService {

//    @GET("?method=artist.search&api_key=a7c02f03da8e2b8cd6432c02062f6e18&format=json")
    @GET("?method=artist.search")
    Observable<ArtistSearch> getArtist(@Query("api_key") String key, @Query("format") String format,
                                             @Query("artist") String name, @Query("limit") int limit);


}
