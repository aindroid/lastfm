
package ainhoamoreno.com.lastfm.search.mapper;

import android.os.Parcel;
import android.os.Parcelable;

public class ArtistMapper implements Parcelable {

    private final String name;
    private String listeners;
    private String mbid;
    private String url;
    private String imageUrl = null;

    public ArtistMapper(String name) {
        this.name = name;
    }

    protected ArtistMapper(Parcel in) {
        name = in.readString();
        listeners = in.readString();
        mbid = in.readString();
        url = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<ArtistMapper> CREATOR = new Creator<ArtistMapper>() {
        @Override
        public ArtistMapper createFromParcel(Parcel in) {
            return new ArtistMapper(in);
        }

        @Override
        public ArtistMapper[] newArray(int size) {
            return new ArtistMapper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(listeners);
        dest.writeString(mbid);
        dest.writeString(url);
        dest.writeString(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
