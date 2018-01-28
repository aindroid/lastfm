package ainhoamoreno.com.lastfm.search;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.data.artist.search.Artist;
import ainhoamoreno.com.lastfm.data.artist.search.ImageType;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ainhoa on 24/01/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<Artist> mResults = new ArrayList<>();
    private final OnArtistClickListener mOnArtistClickListener;
    private final @ImageType.Type String mImageType;

    public SearchAdapter(@ImageType.Type String imageType, OnArtistClickListener listener) {
        mImageType = imageType;
        mOnArtistClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView) TextView mTextView;
        @BindView(R.id.imageView)  ImageView mImageView;
        @BindView(R.id.artistItemView)  View mView;

        @BindDimen(R.dimen.small_img_size) float smallImgSize;
        @BindDimen(R.dimen.medium_img_size) float mediumImgSize;
        @BindDimen(R.dimen.large_img_size) float largeImgSize;

        public ViewHolder(View v, @ImageType.Type String imageType) {
            super(v);

            ButterKnife.bind(this, v);

            final int imageSize;
            switch (imageType) {
                case ImageType.SMALL:
                    imageSize = (int) smallImgSize;
                    break;
                case ImageType.MEDIUM:
                    imageSize = (int) mediumImgSize;
                    break;
                default:
                    imageSize = (int) largeImgSize;
                    break;
            }

            mImageView.getLayoutParams().height = imageSize;
            mImageView.getLayoutParams().width = imageSize;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);

        return new ViewHolder(v, mImageType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Artist artist = mResults.get(position);
        holder.mTextView.setText(artist.name);



        ViewCompat.setTransitionName(holder.mImageView, artist.name);

        //String url = artist.image.get(2).text; //175x175
        String url = artist.image.get(3).text; //300x300
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(holder.mImageView.getContext())
                    .load(url)
                    .into(holder.mImageView);
        }

        holder.mView.setOnClickListener(v -> {
            ArtistItem artistItem = new ArtistItem(artist.name);
            artistItem.setImageUrl(url);
            artistItem.setMbid(artist.mbid);
            mOnArtistClickListener.onClick(position, artistItem, holder.mImageView);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void setData(List<Artist> data) {
        mResults.clear();
        mResults.addAll(data);

        notifyDataSetChanged();
    }

    interface OnArtistClickListener {
        void onClick(int position, ArtistItem artistItem, ImageView imageView);
    }
}
