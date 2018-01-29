
package ainhoamoreno.com.lastfm.model.artist.getInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("tag")
    @Expose
    public List<Tag> tag = null;

}
