package ainhoamoreno.com.lastfm.search;

import android.widget.ImageView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import ainhoamoreno.com.lastfm.R;
import ainhoamoreno.com.lastfm.artistdetail.ArtistDetailActivity;
import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class SearchArtistPresenterTest {

    @Mock
    private SearchContract.View mockView;
    @Mock
    private ArtistDataProvider mockDataProvider;
    @Mock
    private List<ArtistItem> mockResults;

    private SearchArtistPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new SearchArtistPresenter(mockView, mockDataProvider);
        presenter.setResults(mockResults);
    }

    @Test
    public void onClick() throws Exception {
        // given
        ArtistItem artistItem = new ArtistItem();
        ImageView imageView = new ImageView(RuntimeEnvironment.application);

        // when
        presenter.onClick(0, artistItem, imageView);

        // verify
        verify(mockView).openRecyclerViewItem(eq(ArtistDetailActivity.class), any(), any());
    }

    @Test
    public void onImgSizeSelectionChanged() throws Exception {
        // gven
        presenter.setArtistQuery("cher");
        ArtistItem artistItem = new ArtistItem();
        Observable<ArtistItem> obs = Observable.just(artistItem);
        when(mockDataProvider.getArtists(anyString(), anyInt())).thenReturn(obs);

        // when
        presenter.changeImgSizeSelection(R.id.mediumRb);

        // verify
        verify(mockView).setUpRecyclerView(any());
        verify(mockDataProvider).getArtists(anyString(), anyInt());
    }

    @Test
    public void onImgSizeSelectionChanged_emptyQueryText() throws Exception {
        // when
        presenter.changeImgSizeSelection(R.id.mediumRb);

        // verify
        verify(mockView).setUpRecyclerView(any());
        verifyNoMoreInteractions(mockView);
        verifyNoMoreInteractions(mockDataProvider);
    }

    @Test
    public void search_showResultsViews() throws Exception {
        // given
        ArtistItem artistItem = new ArtistItem();
        Observable<ArtistItem> obs = Observable.just(artistItem);
        when(mockDataProvider.getArtists(anyString(), anyInt())).thenReturn(obs);

        when(mockResults.isEmpty()).thenReturn(false);

        // when
        presenter.search("anyArtist");

        // verify
        verify(mockView).showLoading();
        verify(mockView).updateResults(any());
        verify(mockView).showResultsViews();
    }

    @Test
    public void search_showNoResultsViews() throws Exception {
        // given
        ArtistItem artistItem = new ArtistItem();
        Observable<ArtistItem> obs = Observable.just(artistItem);
        when(mockDataProvider.getArtists(anyString(), anyInt())).thenReturn(obs);

        when(mockResults.isEmpty()).thenReturn(true);

        // when
        presenter.search("anyArtist");

        // verify
        verify(mockView).showLoading();
        verify(mockView).updateResults(any());
        verify(mockView).showNoResultsViews();
    }
}