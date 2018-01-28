
package ainhoamoreno.com.lastfm.data.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("#text")
    @Expose
    public String text;
    @SerializedName("rel")
    @Expose
    public String rel;
    @SerializedName("href")
    @Expose
    public String href;

}
