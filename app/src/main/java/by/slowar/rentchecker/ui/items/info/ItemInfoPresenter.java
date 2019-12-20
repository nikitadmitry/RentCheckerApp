package by.slowar.rentchecker.ui.items.info;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class ItemInfoPresenter extends BasePresenter<ItemInfoMvp.View> implements ItemInfoMvp.Presenter {

    private ItemInfoMvp.Router router;

    @Inject
    public ItemInfoPresenter(@NonNull ItemInfoMvp.Router router) {
        this.router = router;
    }

    @Override
    public void callButtonClicked(String number) {
        router.showNumberDialScreen(number);
    }
}