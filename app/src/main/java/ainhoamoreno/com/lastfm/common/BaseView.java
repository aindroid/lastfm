package ainhoamoreno.com.lastfm.common;

/**
 * Created by ainhoa on 29/01/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
