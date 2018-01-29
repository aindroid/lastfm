
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("url")
    @Expose
    public String url;

}
