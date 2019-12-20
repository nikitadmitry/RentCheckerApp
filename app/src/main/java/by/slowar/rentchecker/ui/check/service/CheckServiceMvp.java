package by.slowar.rentchecker.ui.check.service;

import java.util.Set;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 14.12.2019.
 */

public final class CheckServiceMvp {

    private CheckServiceMvp() {
    }

    public interface View extends MvpView {

        void sendNewItems(Set<Item> items);

        void notifyNewItem();
    }

    public interface Presenter extends MvpPresenter<CheckServiceMvp.View> {

        void onServiceReady();

        Set<Item> onItemsRequest();

        void onParametersChanged();

        void onDestroy();
    }


}