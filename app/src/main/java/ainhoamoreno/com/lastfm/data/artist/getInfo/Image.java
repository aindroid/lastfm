
package ainhoamoreno.com.lastfm.data.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    @Expose
    public String text;
    @SerializedName("size")
    @Expose
    public String size;

}