
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistGetInfo {

    @SerializedName("artist")
    @Expose
    public Artist artist;

}
