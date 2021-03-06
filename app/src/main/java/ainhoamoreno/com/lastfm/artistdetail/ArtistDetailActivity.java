package ainhoamoreno.com.lastfm.artistdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artistdetail.constants.Extras;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ArtistDetailActivity extends DaggerAppCompatActivity implements ArtistDetailContract.View {

    @Inject ArtistDetailContract.Presenter mPresenter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.imageViewDetail) ImageView mImageView;
    @BindView(R.id.contentView) TextView mContentView;

    @BindColor(android.R.color.transparent) int mTransparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);

        supportPostponeEnterTransition();

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String artistName = extras.getString(Extras.EXTRA_ARTIST_NAME);
        String imageUrl = extras.getString(Extras.EXTRA_ARTIST_IMG_URL);
        String mbid = extras.getString(Extras.EXTRA_ARTIST_MBID);

        setUpToolbar(artistName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(Extras.EXTRA_ARTIST_IMAGE_TRANSITION_NAME);
            mImageView.setTransitionName(imageTransitionName);
        }

        if (!TextUtils.isEmpty(imageUrl)) {
            loadImage(imageUrl);
        }

        mPresenter.getArtistInfo(mbid);
    }

    @Override
    public void setUpToolbar(String title) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        mToolbar.setTitle(title);
        mToolbar.setBackgroundColor(mTransparent);
    }

    @Override
    public void updateArtistImg(@Nullable final Bitmap bitmap) {
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }

        supportStartPostponedEnterTransition();
    }

    @Override
    public void updateArtistContent(String content) {
        mContentView.setText(content);
    }

    public void loadImage(@NonNull String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        updateArtistImg(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        updateArtistImg(null);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.unSubscribe();
    }
}
