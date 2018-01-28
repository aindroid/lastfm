
package ainhoamoreno.com.lastfm.data.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    @Expose
    public String text;
    @SerializedName("size")
    @Expose
    public String size;

    public Image withText(String text) {
        this.text = text;
        return this;
    }

    public Image withSize(String size) {
        this.size = size;
        return this;
    }

}
