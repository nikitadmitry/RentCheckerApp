package by.slowar.rentchecker.ui.map;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 04.12.2019.
 */

public final class MapMvp {

    private MapMvp() {
    }

    public interface View extends MvpView {
    }

    public interface Presenter extends MvpPresenter<MapMvp.View> {

        void itemClicked(Item item);
    }

    public interface Router {

        void showItemInfo(Item item);
    }
}