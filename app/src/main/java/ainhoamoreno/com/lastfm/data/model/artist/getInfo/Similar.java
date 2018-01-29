
package ainhoamoreno.com.lastfm.data.model.artist.getInfo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    private List<SimilarArtists> artist = new ArrayList<>();

    public List<SimilarArtists> getArtist() {
        return artist;
    }
}
