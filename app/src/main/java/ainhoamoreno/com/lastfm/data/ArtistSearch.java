
package ainhoamoreno.com.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistSearch {

    @SerializedName("results")
    @Expose
    public Results results;

    public ArtistSearch withResults(Results results) {
        this.results = results;
        return this;
    }

}
