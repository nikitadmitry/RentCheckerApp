package by.slowar.rentchecker.ui.items;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 03.12.2019.
 */

public final class ItemsMvp {

    private ItemsMvp() {
    }

    public interface View extends MvpView {
    }

    public interface Presenter extends MvpPresenter<ItemsMvp.View> {

        void itemClicked(Item item);
    }

    public interface Router {

        void showItemInfo(Item item);
    }
}