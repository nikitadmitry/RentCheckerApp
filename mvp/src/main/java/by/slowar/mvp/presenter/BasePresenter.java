package by.slowar.mvp.presenter;

import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 02.12.2019.
 */

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
    }

    @Override
    public T getView() {
        return view;
    }
}