
package ainhoamoreno.com.lastfm.data.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr {

    @SerializedName("for")
    @Expose
    private String _for;

    public String get_for() {
        return _for;
    }
}
