package ainhoamoreno.com.lastfm.mapper;

import android.support.annotation.NonNull;

public class ArtistItem {

    private final String mName;
    private String mMbid;
    private String mImageUrl;
    private String mContent;

    public ArtistItem(@NonNull String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getMbid() {
        return mMbid;
    }

    public void setMbid(String mbid) {
        this.mMbid = mbid;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

}
