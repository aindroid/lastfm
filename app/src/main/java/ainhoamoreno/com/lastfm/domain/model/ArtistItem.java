package ainhoamoreno.com.lastfm.domain.model;

import android.support.annotation.NonNull;

import ainhoamoreno.com.lastfm.data.model.artist.Artist;

public class ArtistItem extends BaseItem {

    private String name;
    private String mbid;
    private String imageUrl;
    private String content;

    public ArtistItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ArtistItem convertTo(@NonNull final Artist artist) {
        ArtistItem artistItem = new ArtistItem();
        artistItem.name = artist.getName();
        artistItem.mbid = artist.getMbid();
        artistItem.imageUrl = artist.getLargeImageUrl();
        artistItem.content = artist.getBio() != null ? artist.getBio().getContent() : null;
        return artistItem;
    }

}
