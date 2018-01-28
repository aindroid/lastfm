package ainhoamoreno.com.lastfm.artist.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.data.artist.getInfo.Bio;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ainhoa on 26/01/2018.
 */

public class ArtistDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.imageViewDetail) ImageView mImageView;
    @BindView(R.id.contentView) TextView mContentView;

    ArtistRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);

        supportPostponeEnterTransition();

        ButterKnife.bind(this);

        repository = new ArtistRepository(LastFmApplication.get().getService());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        Bundle extras = getIntent().getExtras();
        ArtistItem animalItem = extras.getParcelable("EXTRA_ARTIST_ITEM");
        mToolbar.setTitle(animalItem.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString("EXTRA_ARTIST_IMAGE_TRANSITION_NAME");
            mImageView.setTransitionName(imageTransitionName);
        }

        String imageUrl = animalItem.getImageUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            loadImage(imageUrl);
        }

        getInfo(animalItem.getMbid());
    }

    private void getInfo(final String mbid) {
        repository.getInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> {
                    Bio bio = artist.bio;
                    mContentView.setText(bio.content);
                });
    }

    private void loadImage(final String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mImageView.setImageBitmap(bitmap);
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }
}
