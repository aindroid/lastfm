package ainhoamoreno.com.lastfm.data.api;

import ainhoamoreno.com.lastfm.BuildConfig;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.ArtistGetInfo;
import ainhoamoreno.com.lastfm.data.model.artist.search.ArtistSearch;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFmArtistApi {

    @GET("?method=artist.search&format=json&api_key="+ BuildConfig.FLASH_FM_API_KEY)
    Observable<ArtistSearch> getArtists(@Query("artist") String name, @Query("page") int page, @Query("limit") int limit);

    @GET("?method=artist.getinfo&format=json&api_key="+ BuildConfig.FLASH_FM_API_KEY)
    Observable<ArtistGetInfo> getArtistInfo(@Query("mbid") String mbid);
}
