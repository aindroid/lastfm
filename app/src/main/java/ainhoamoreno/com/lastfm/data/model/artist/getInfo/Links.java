
package ainhoamoreno.com.lastfm.data.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("link")
    @Expose
    private Link link;

    public Link getLink() {
        return link;
    }
}
