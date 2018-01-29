package ainhoamoreno.com.lastfm.data.model.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Bio;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Similar;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Stats;
import ainhoamoreno.com.lastfm.data.model.artist.getInfo.Tags;

public class Artist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("ontour")
    @Expose
    private String ontour;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("similar")
    @Expose
    private Similar similar;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("bio")
    @Expose
    private Bio bio;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("image")
    @Expose
    private List<Image> image = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public String getStreamable() {
        return streamable;
    }

    public String getOntour() {
        return ontour;
    }

    public Stats getStats() {
        return stats;
    }

    public Similar getSimilar() {
        return similar;
    }

    public Tags getTags() {
        return tags;
    }

    public Bio getBio() {
        return bio;
    }

    public String getListeners() {
        return listeners;
    }

    public List<Image> getImage() {
        return image;
    }

    public String getSmallImage() {
        return image.size() > 0 ? image.get(0).getText() : null;
    }

    public String getMediumImage() {
        return image.size() > 1 ? image.get(1).getText() : null;
    }

    public String getLargeImageUrl() {
        //300x300
        return image.size() > 2 ? image.get(2).getText() : null;
    }

}
