package ainhoamoreno.com.lastfm.repository;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.data.Artist;
import ainhoamoreno.com.lastfm.network.LastFmService;
import ainhoamoreno.com.lastfm.util.Resources;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ainhoa on 24/01/2018.
 */

public class ArtistRepository {

    final LastFmService service;

    public ArtistRepository(LastFmService service) {
        this.service = service;
    }

    public Observable<Artist> getSearch(String name) {
        return service.getArtist(Resources.getString(R.string.flash_fm_api_key), "json", name, 100)
                .subscribeOn(Schedulers.io())
                .filter(artistSearch -> artistSearch != null
                        && artistSearch.results != null
                        && artistSearch.results.artistmatches != null
                        && artistSearch.results.artistmatches.artist != null)
                .flatMap(artistSearch -> Observable.fromIterable(artistSearch.results.artistmatches.artist))
                .filter(artist -> artist != null);
    }

}
