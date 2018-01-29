
package ainhoamoreno.com.lastfm.data.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.data.model.artist.Image;

public class SimilarArtists {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<Image> image = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Image> getImage() {
        return image;
    }
}
