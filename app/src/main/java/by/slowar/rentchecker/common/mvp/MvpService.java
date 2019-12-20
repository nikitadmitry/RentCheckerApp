package by.slowar.rentchecker.common.mvp;

import android.app.Service;

import androidx.annotation.NonNull;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 14.12.2019.
 */

public abstract class MvpService<V extends MvpView> extends Service {

    @NonNull
    public abstract MvpPresenter<V> getPresenter();

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate() {
        super.onCreate();
        getPresenter().attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}