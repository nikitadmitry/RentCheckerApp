package by.slowar.rentchecker.common.mvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;

/**
 * Created by SlowAR on 04.12.2019.
 */

public abstract class MvpActivity<V extends MvpView> extends AppCompatActivity {

    @NonNull
    public abstract MvpPresenter<V> getPresenter();

    @SuppressWarnings("unchecked")
    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().attachView((V) this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().detachView();
        if (isFinishing()) {
            getPresenter().destroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }
}
