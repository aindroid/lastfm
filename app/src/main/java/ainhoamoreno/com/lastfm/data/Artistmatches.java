
package ainhoamoreno.com.lastfm.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artistmatches {

    @SerializedName("artist")
    @Expose
    public List<Artist> artist = null;

    public Artistmatches withArtist(List<Artist> artist) {
        this.artist = artist;
        return this;
    }

}