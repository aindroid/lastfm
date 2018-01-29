
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("listeners")
    @Expose
    public String listeners;
    @SerializedName("playcount")
    @Expose
    public String playcount;

}
