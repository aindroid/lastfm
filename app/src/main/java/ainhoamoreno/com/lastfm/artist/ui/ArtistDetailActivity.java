package ainhoamoreno.com.lastfm.artist.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artist.model.ArtistItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ainhoa on 26/01/2018.
 */

public class ArtistDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageViewDetail) ImageView mImageView;
    @BindView(R.id.mainView) View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_detail);

        supportPostponeEnterTransition();

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString("EXTRA_ARTIST_IMAGE_TRANSITION_NAME");
            mImageView.setTransitionName(imageTransitionName);
        }

        ArtistItem animalItem = extras.getParcelable("EXTRA_ARTIST_ITEM");
        String imageUrl = animalItem.getImageUrl();
//        mView.post(() -> {
//            loadImage(imageUrl, mView.getWidth());
//        });
        loadImage(imageUrl, 1080);
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
