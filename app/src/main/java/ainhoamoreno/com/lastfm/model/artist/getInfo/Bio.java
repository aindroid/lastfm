
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bio {

    @SerializedName("links")
    @Expose
    public Links links;
    @SerializedName("published")
    @Expose
    public String published;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("content")
    @Expose
    public String content;

}
