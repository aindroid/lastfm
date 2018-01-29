package ainhoamoreno.com.lastfm.data.api;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Artist;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Bio;
import ainhoamoreno.com.lastfm.mapper.ArtistItem;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class LastFmArtistApiImpl {

    private final LastFmArtistApi mService;

    @Inject
    public LastFmArtistApiImpl(LastFmArtistApi service) {
        mService = service;
    }

    public Observable<ArtistItem> searchArtists(String name, int page) {
        return mService.getArtist(name, page, 20)
                .subscribeOn(Schedulers.io())
                .filter(artistSearch -> artistSearch != null
                        && artistSearch.getResults() != null
                        && artistSearch.getResults().getArtistmatches() != null
                        && artistSearch.getResults().getArtistmatches().getArtist() != null)
                .flatMap(artistSearch -> Observable.fromIterable(artistSearch.getResults().getArtistmatches().getArtist()))
                .filter(artist -> artist != null)
                .map(artist -> {
                    ArtistItem artistItem = new ArtistItem(artist.getName());
                    artistItem.setImageUrl(artist.getLargeImageUrl());
                    artistItem.setMbid(artist.getMbid());
                    return artistItem;
                });
    }

    public Observable<ArtistItem> getArtistInfo(String mbid) {
        return mService.getArtistInfo(mbid)
                .subscribeOn(Schedulers.io())
                .filter(artistSearch -> artistSearch != null
                        && artistSearch.getArtist() != null
                        && artistSearch.getArtist().getBio() != null)
                .map(artistGetInfo -> {
                    Artist artist = artistGetInfo.getArtist();
                    ArtistItem artistItem = new ArtistItem(artist.getName());
                    Bio bio = artist.getBio();
                    if (bio.getContent() != null) {
                        artistItem.setContent(bio.getContent());
                    } else if (bio.getSummary() != null) {
                        artistItem.setContent(bio.getSummary());
                    }

                    return artistItem;
                });
    }
}
