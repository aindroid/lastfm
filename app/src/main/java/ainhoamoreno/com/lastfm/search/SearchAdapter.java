package ainhoamoreno.com.lastfm.search;

import android.support.annotation.NonNull;
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
import ainhoamoreno.com.lastfm.data.model.artist.ImageType;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<ArtistItem> mResults = new ArrayList<>();
    private final OnArtistClickListener mOnArtistClickListener;
    private final @ImageType.Type String mImageType;

    SearchAdapter(@ImageType.Type String imageType, OnArtistClickListener listener) {
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

        ViewHolder(View v, @ImageType.Type String imageType) {
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
        ArtistItem artist = mResults.get(position);
        holder.mTextView.setText(artist.getName());

        ViewCompat.setTransitionName(holder.mImageView, artist.getName());

        String url = artist.getImageUrl();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(holder.mImageView.getContext())
                    .load(url)
                    .into(holder.mImageView);
        }

        holder.mView.setOnClickListener(v -> {
            mOnArtistClickListener.onClick(position, artist, holder.mImageView);
        });
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void setData(@NonNull List<ArtistItem> data) {
        mResults.clear();
        mResults.addAll(data);

        notifyDataSetChanged();
    }

    interface OnArtistClickListener {
        void onClick(int position, @NonNull ArtistItem artistItem, @NonNull ImageView imageView);
    }
}
