
package ainhoamoreno.com.lastfm.data.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Artistmatches {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist = new ArrayList<>();

    public List<Artist> getArtist() {
        return artist;
    }
}
