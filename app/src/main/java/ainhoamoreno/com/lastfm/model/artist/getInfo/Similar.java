
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    public List<SimilarArtists> artist = null;

}
