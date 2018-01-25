
package ainhoamoreno.com.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpensearchQuery {

    @SerializedName("#text")
    @Expose
    public String text;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("searchTerms")
    @Expose
    public String searchTerms;
    @SerializedName("startPage")
    @Expose
    public String startPage;

    public OpensearchQuery withText(String text) {
        this.text = text;
        return this;
    }

    public OpensearchQuery withRole(String role) {
        this.role = role;
        return this;
    }

    public OpensearchQuery withSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
        return this;
    }

    public OpensearchQuery withStartPage(String startPage) {
        this.startPage = startPage;
        return this;
    }

}
