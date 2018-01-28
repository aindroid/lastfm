
package ainhoamoreno.com.lastfm.data.artist.getInfo;

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
