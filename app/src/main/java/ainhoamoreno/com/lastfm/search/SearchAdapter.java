package ainhoamoreno.com.lastfm.search;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import ainhoamoreno.com.lastfm.data.Artist;

/**
 * Created by ainhoa on 24/01/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<Artist> mResults = new ArrayList<>();
    private final OnArtistClickListener mOnArtistClickListener;
    private final float mImageSize;

    public SearchAdapter(float imageSize, OnArtistClickListener listener) {
        mImageSize = imageSize;
        mOnArtistClickListener = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public View mView;

        public ViewHolder(View v, int imageSize) {
            super(v);

            mView = v;

            mTextView = v.findViewById(R.id.textView);
            mImageView = v.findViewById(R.id.imageView);
            mImageView.getLayoutParams().height = imageSize;
            mImageView.getLayoutParams().width = imageSize;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v, (int) mImageSize);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mResults.size() > position) {
            Artist artist = mResults.get(position);
            holder.mTextView.setText(artist.name);

            ViewCompat.setTransitionName(holder.mImageView, artist.name);

            //String url = artist.image.get(2).text; //175x175
            String url = artist.image.get(3).text; //300x300
            Log.e("pepe", "url = " + url);
            if (!TextUtils.isEmpty(url)) {
                Picasso.with(holder.mImageView.getContext())
                        .load(url)
                        .into(holder.mImageView);
            }

            holder.mView.setOnClickListener(v -> {
                ArtistItem artistItem = new ArtistItem(artist.name);
                artistItem.setImageUrl(artist.url);
                mOnArtistClickListener.onClick(position, artistItem, holder.mImageView);
            });
        }
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
