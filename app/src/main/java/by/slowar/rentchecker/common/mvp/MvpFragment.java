package by.slowar.rentchecker.common.mvp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 04.12.2019.
 */
public abstract class MvpFragment<V extends MvpView> extends Fragment {

    @NonNull
    public abstract MvpPresenter<V> getPresenter();

    @SuppressWarnings("unchecked")
    @Override
    public void onStart() {
        super.onStart();
        getPresenter().attachView((V) this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }
}