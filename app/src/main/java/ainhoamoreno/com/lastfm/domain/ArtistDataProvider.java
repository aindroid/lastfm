package ainhoamoreno.com.lastfm.domain;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.data.ArtistService;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ArtistDataProvider {

    private final ArtistService mService;

    @Inject
    public ArtistDataProvider(ArtistService service) {
        mService = service;
    }

    public Observable<ArtistItem> getArtists(@NonNull String name, int page) {
        return mService.getArtists(name, page, 20)
                .subscribeOn(Schedulers.io())
                .flatMap(artistSearch -> Observable.
                        fromIterable(artistSearch.getResults().getArtistmatches().getArtist()))
                .map(ArtistItem::convertTo)
                .onErrorResumeNext(throwable -> {
                    // todo: error handler needs implementing. For now we silence the errors coming from the server
                    return Observable.empty();
                });
    }


    public Observable<ArtistItem> getArtistInfo(@NonNull String mbid) {
        return mService.getArtistInfo(mbid)
                .subscribeOn(Schedulers.io())
                .flatMap(artistGetInfo -> Observable.just(artistGetInfo.getArtist()))
                .map(ArtistItem::convertTo)
                .onErrorResumeNext(throwable -> {
                    // todo: same todo as above
                    return Observable.empty();
                });
    }

}
