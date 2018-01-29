
package ainhoamoreno.com.lastfm.data.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Tags {

    @SerializedName("tag")
    @Expose
    private List<Tag> tag = new ArrayList<>();

    public List<Tag> getTag() {
        return tag;
    }
}
