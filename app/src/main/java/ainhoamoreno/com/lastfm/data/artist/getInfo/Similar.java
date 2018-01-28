
package ainhoamoreno.com.lastfm.data.artist.getInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    public List<SimilarArtists> artist = null;

}
