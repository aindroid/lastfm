
package ainhoamoreno.com.lastfm.data.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }
}
