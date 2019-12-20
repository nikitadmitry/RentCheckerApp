package by.slowar.rentchecker.ui.check;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class CheckPresenter extends BasePresenter<CheckMvp.View> implements CheckMvp.Presenter {

    private CheckMvp.Router router;

    @Inject
    public CheckPresenter(@NonNull CheckMvp.Router router) {
        this.router = router;
    }

    @Override
    public void parametersButtonClicked() {
        router.showParametersScreen();
    }
}