
package ainhoamoreno.com.lastfm.data.model.artist.getInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ainhoamoreno.com.lastfm.data.model.artist.Artist;

public class GetInfo {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }
}
