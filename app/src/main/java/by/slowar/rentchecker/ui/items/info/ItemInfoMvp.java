package by.slowar.rentchecker.ui.items.info;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 04.12.2019.
 */

public final class ItemInfoMvp {

    private ItemInfoMvp() {
    }

    public interface View extends MvpView {

        void onCallButtonClick(String number);
    }

    public interface Presenter extends MvpPresenter<View> {

        void callButtonClicked(String number);
    }

    public interface Router {

        void showNumberDialScreen(String number);
    }
}