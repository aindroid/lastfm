
package ainhoamoreno.com.lastfm.data.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpensearchQuery {

    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("searchTerms")
    @Expose
    private String searchTerms;
    @SerializedName("startPage")
    @Expose
    private String startPage;

    public String getText() {
        return text;
    }

    public String getRole() {
        return role;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public String getStartPage() {
        return startPage;
    }
}
