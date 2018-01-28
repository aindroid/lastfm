
package ainhoamoreno.com.lastfm.data.artist.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("listeners")
    @Expose
    public String listeners;
    @SerializedName("mbid")
    @Expose
    public String mbid;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("streamable")
    @Expose
    public String streamable;
    @SerializedName("image")
    @Expose
    public List<Image> image = null;

    public Artist withName(String name) {
        this.name = name;
        return this;
    }

    public Artist withListeners(String listeners) {
        this.listeners = listeners;
        return this;
    }

    public Artist withMbid(String mbid) {
        this.mbid = mbid;
        return this;
    }

    public Artist withUrl(String url) {
        this.url = url;
        return this;
    }

    public Artist withStreamable(String streamable) {
        this.streamable = streamable;
        return this;
    }

    public Artist withImage(List<Image> image) {
        this.image = image;
        return this;
    }

}
