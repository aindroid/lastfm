
package ainhoamoreno.com.lastfm.artist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ArtistItem implements Parcelable {

    private final String name;
    private String listeners;
    private String mbid;
    private String url;
    private String imageUrl = null;

    public ArtistItem(String name) {
        this.name = name;
    }

    protected ArtistItem(Parcel in) {
        name = in.readString();
        listeners = in.readString();
        mbid = in.readString();
        url = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<ArtistItem> CREATOR = new Creator<ArtistItem>() {
        @Override
        public ArtistItem createFromParcel(Parcel in) {
            return new ArtistItem(in);
        }

        @Override
        public ArtistItem[] newArray(int size) {
            return new ArtistItem[size];
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
}
