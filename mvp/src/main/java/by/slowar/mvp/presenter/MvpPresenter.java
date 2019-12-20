package by.slowar.mvp.presenter;

import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 02.12.2019.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    void destroy();

    V getView();
}
