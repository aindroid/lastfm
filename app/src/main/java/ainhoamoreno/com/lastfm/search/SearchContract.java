package ainhoamoreno.com.lastfm.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import ainhoamoreno.com.lastfm.common.base.BasePresenter;
import ainhoamoreno.com.lastfm.common.base.BaseView;
import ainhoamoreno.com.lastfm.model.artist.search.ImageType;

public interface SearchContract {

    interface View extends BaseView {

        void showNoResults();

        void showResults();

        void showLoading();

        void openRecyclerViewItem(@NonNull Class<? extends Activity> intentClass,
                                  @NonNull Bundle extras,
                                  @NonNull android.view.View transitionView);

        RecyclerView getRecyclerView();
    }

    interface Presenter extends BasePresenter {

        void search(@NonNull String artistName);

        void onImgSizeSelectionChanged(@IdRes int viewId);

        void setUpRecyclerView(@ImageType.Type String imgType);
    }
}
