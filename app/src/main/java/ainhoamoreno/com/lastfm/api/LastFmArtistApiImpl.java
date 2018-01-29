package ainhoamoreno.com.lastfm.api;

import ainhoamoreno.com.lastfm.model.artist.search.Artist;
import ainhoamoreno.com.lastfm.network.LastFmArtistApi;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LastFmArtistApiImpl {

    //fixme this is the interactor

    final LastFmArtistApi service;

    public LastFmArtistApiImpl(LastFmArtistApi service) {
        this.service = service;
    }

    public Observable<Artist> getSearch(String name, int page) {
        return service.getArtist(name, page, 20)
                .subscribeOn(Schedulers.io())
                .filter(artistSearch -> artistSearch != null
                        && artistSearch.results != null
                        && artistSearch.results.artistmatches != null
                        && artistSearch.results.artistmatches.artist != null)
                .flatMap(artistSearch -> Observable.fromIterable(artistSearch.results.artistmatches.artist))
                .filter(artist -> artist != null);
    }


    public Observable<ainhoamoreno.com.lastfm.model.artist.getInfo.Artist> getInfo(String mbid) {
        return service.getArtistInfo(mbid)
                .subscribeOn(Schedulers.io())
                .filter(artistSearch -> artistSearch != null
                        && artistSearch.artist != null)
                .map(artistGetInfo -> artistGetInfo.artist);
    }

}
