package by.slowar.rentchecker.ui.check;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 03.12.2019.
 */

public final class CheckMvp {

    private CheckMvp() {
    }

    public interface View extends MvpView {
    }

    public interface Presenter extends MvpPresenter<CheckMvp.View> {

        void parametersButtonClicked();
    }

    public interface Router {

        void showParametersScreen();
    }
}