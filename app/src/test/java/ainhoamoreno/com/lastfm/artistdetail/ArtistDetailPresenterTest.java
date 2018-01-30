package ainhoamoreno.com.lastfm.artistdetail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ainhoamoreno.com.lastfm.domain.ArtistDataProvider;
import ainhoamoreno.com.lastfm.domain.model.ArtistItem;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtistDetailPresenterTest {

    @Mock
    private ArtistDetailContract.View mockView;
    @Mock
    private ArtistDataProvider mockDataProvider;

    private ArtistDetailPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new ArtistDetailPresenter(mockView, mockDataProvider);
    }

    @Test
    public void getArtistInfo() throws Exception {
        // given
        ArtistItem artistItem = new ArtistItem();
        Observable<ArtistItem> obs = Observable.just(artistItem);
        when(mockDataProvider.getArtistInfo(anyString())).thenReturn(obs);

        // when
        presenter.getArtistInfo(anyString());

        // verify
        verify(mockDataProvider).getArtistInfo(anyString());
        verify(mockView).updateArtistContent(null);
    }

}