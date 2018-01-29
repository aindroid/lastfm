
package ainhoamoreno.com.lastfm.model.artist.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("opensearch:Query")
    @Expose
    public OpensearchQuery opensearchQuery;
    @SerializedName("opensearch:totalResults")
    @Expose
    public String opensearchTotalResults;
    @SerializedName("opensearch:startIndex")
    @Expose
    public String opensearchStartIndex;
    @SerializedName("opensearch:itemsPerPage")
    @Expose
    public String opensearchItemsPerPage;
    @SerializedName("artistmatches")
    @Expose
    public Artistmatches artistmatches;
    @SerializedName("@attr")
    @Expose
    public Attr attr;

    public Results withOpensearchQuery(OpensearchQuery opensearchQuery) {
        this.opensearchQuery = opensearchQuery;
        return this;
    }

    public Results withOpensearchTotalResults(String opensearchTotalResults) {
        this.opensearchTotalResults = opensearchTotalResults;
        return this;
    }

    public Results withOpensearchStartIndex(String opensearchStartIndex) {
        this.opensearchStartIndex = opensearchStartIndex;
        return this;
    }

    public Results withOpensearchItemsPerPage(String opensearchItemsPerPage) {
        this.opensearchItemsPerPage = opensearchItemsPerPage;
        return this;
    }

    public Results withArtistmatches(Artistmatches artistmatches) {
        this.artistmatches = artistmatches;
        return this;
    }

    public Results withAttr(Attr attr) {
        this.attr = attr;
        return this;
    }

}
