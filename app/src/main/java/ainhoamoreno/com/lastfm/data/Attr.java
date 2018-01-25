
package ainhoamoreno.com.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr {

    @SerializedName("for")
    @Expose
    public String _for;

    public Attr withFor(String _for) {
        this._for = _for;
        return this;
    }

}
