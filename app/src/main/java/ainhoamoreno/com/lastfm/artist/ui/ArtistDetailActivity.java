package ainhoamoreno.com.lastfm.artist.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ainhoamoreno.com.lastfm.LastFmApplication;
import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import ainhoamoreno.com.lastfm.data.artist.getInfo.Bio;
import ainhoamoreno.com.lastfm.data.artist.search.Artist;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by ainhoa on 26/01/2018.
 */

public class ArtistDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.imageViewDetail) ImageView mImageView;
    @BindView(R.id.mainView) View mView;
    @BindView(R.id.summaryView) TextView mSummaryView;

    ArtistRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail_);

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
        mView.post(() -> {
            if (!TextUtils.isEmpty(imageUrl)) {
                loadImage(imageUrl, mView.getWidth());
            }
        });

        getInfo(animalItem.getMbid());
    }

    private void getInfo(final String mbid) {
        final List<Artist> list = new ArrayList<>();
        repository.getInfo(mbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artist -> {
                    Bio bio = artist.bio;
                    mSummaryView.setText(bio.summary);
                });
    }

    private void loadImage(String imageUrl, int size) {
        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .resize(size, size)
                .noFade()
                .into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
    }
}
