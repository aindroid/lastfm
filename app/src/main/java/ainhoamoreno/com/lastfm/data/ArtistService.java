package ainhoamoreno.com.lastfm.data;

import ainhoamoreno.com.lastfm.BuildConfig;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.GetInfo;
import ainhoamoreno.com.lastfm.data.model.artist.search.Search;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArtistService {

    @GET("?method=artist.search&format=json&api_key="+ BuildConfig.FLASH_FM_API_KEY)
    Observable<Search> getArtists(@Query("artist") String name, @Query("page") int page, @Query("limit") int limit);

    @GET("?method=artist.getinfo&format=json&api_key="+ BuildConfig.FLASH_FM_API_KEY)
    Observable<GetInfo> getArtistInfo(@Query("mbid") String mbid);
}
