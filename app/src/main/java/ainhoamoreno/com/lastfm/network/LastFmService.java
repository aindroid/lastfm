package ainhoamoreno.com.lastfm.network;

import ainhoamoreno.com.lastfm.data.artist.getInfo.ArtistGetInfo;
import ainhoamoreno.com.lastfm.data.artist.search.ArtistSearch;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmService {

    @GET("?method=artist.search")
    Observable<ArtistSearch> getArtist(@Query("api_key") String key, @Query("format") String format,
                                             @Query("artist") String name, @Query("limit") int limit);


    @GET("?method=artist.getinfo")
    Observable<ArtistGetInfo> getArtistInfo(@Query("api_key") String key, @Query("format") String format,
                                            @Query("mbid") String mbid, @Query("limit") int limit);
}
