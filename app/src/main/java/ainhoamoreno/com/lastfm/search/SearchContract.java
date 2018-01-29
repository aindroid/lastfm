package ainhoamoreno.com.lastfm.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import ainhoamoreno.com.lastfm.common.base.BasePresenter;
import ainhoamoreno.com.lastfm.common.base.BaseView;
import ainhoamoreno.com.lastfm.model.artist.search.ImageType;

/**
 * Created by ainhoa on 28/01/2018.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        Context getContext();

        void showNoResults();

        void showResults();

        void showLoading();

        void openRecyclerViewItem(@NonNull Class<? extends Activity> intentClass,
                                  @NonNull Bundle extras,
                                  @NonNull android.view.View transitionView);

        RecyclerView getRecyclerView();
    }

    interface Presenter extends BasePresenter {

        void search(String artistName);

        void onImgSizeSelectionChanged(@IdRes int viewId);

        void createRecyclerViewAdapter(@ImageType.Type String imgType);
    }
}
