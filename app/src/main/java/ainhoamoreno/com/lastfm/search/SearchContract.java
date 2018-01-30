package ainhoamoreno.com.lastfm.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import java.util.List;

import ainhoamoreno.com.lastfm.common.BasePresenter;
import ainhoamoreno.com.lastfm.common.BaseView;
import ainhoamoreno.com.lastfm.data.model.artist.ImageType;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;

public interface SearchContract {

    interface View extends BaseView {

        void showNoResultsViews();

        void showResultsViews();

        void showLoading();

        void openRecyclerViewItem(@NonNull Class<? extends Activity> intentClass,
                                  @NonNull Bundle extras,
                                  @NonNull android.view.View transitionView);

        void setUpRecyclerView(@ImageType.Type String imgType);

        void updateResults(List<ArtistItem> adapter);
    }

    interface Presenter extends BasePresenter {

        void search(@NonNull String artistName);

        void onImgSizeSelectionChanged(@IdRes int viewId);

        boolean isLoading();

        void loadNextPage();

        SearchAdapter createAdapter(@ImageType.Type String imgType);
    }
}
