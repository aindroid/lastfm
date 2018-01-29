package ainhoamoreno.com.lastfm.search;

import android.content.Intent;
import android.os.Bundle;

import ainhoamoreno.com.lastfm.common.BasePresenter;
import ainhoamoreno.com.lastfm.common.BaseView;

/**
 * Created by ainhoa on 28/01/2018.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showNoResults();

        void showResults();

        void showLoading();

        void goToArtistDetailActivity(Intent intent, Bundle bundle);
    }

    interface Presenter extends BasePresenter {

        void search(String artistName);

        void onImgSizeSelectionChanged();
    }
}
