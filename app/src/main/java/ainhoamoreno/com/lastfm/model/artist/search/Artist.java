
package ainhoamoreno.com.lastfm.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Artist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("image")
    @Expose
    private List<Image> image = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getListeners() {
        return listeners;
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

    public List<Image> getImage() {
        return image;
    }

    public Image getSmallImage() {
        return image.size() > 0 ? image.get(0) : null;
    }

    public Image getMediumImage() {
        return image.size() > 1 ? image.get(1) : null;
    }

    public String getLargeImageUrl() {
        //300x300
        return image.size() > 2 ? image.get(2).text : null;
    }


}
