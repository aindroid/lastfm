package ainhoamoreno.com.lastfm.search;

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
import ainhoamoreno.com.lastfm.data.Artist;

/**
 * Created by ainhoa on 24/01/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Artist> mArtists = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.textView);
            mImageView = v.findViewById(R.id.imageView);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_detail, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mArtists.size() > position) {
            Artist artist = mArtists.get(position);
            holder.mTextView.setText(artist.name);
            String url = artist.image.get(2).text;
            Log.e("pepe", "url = " + url);
            if (!TextUtils.isEmpty(url)) {
                Picasso.with(holder.mImageView.getContext())
                        .load(url)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .fit()
                        .into(holder.mImageView);
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public void setData(List<Artist> data) {
        mArtists.clear();
        mArtists.addAll(data);

        notifyDataSetChanged();
    }
}
