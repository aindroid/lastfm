
package ainhoamoreno.com.lastfm.data.artist.getInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("tag")
    @Expose
    public List<Tag> tag = null;

}
