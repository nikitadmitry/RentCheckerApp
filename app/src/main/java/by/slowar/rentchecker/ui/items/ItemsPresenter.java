package by.slowar.rentchecker.ui.items;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class ItemsPresenter extends BasePresenter<ItemsMvp.View> implements ItemsMvp.Presenter {

    private ItemsMvp.Router router;

    @Inject
    public ItemsPresenter(@NonNull ItemsMvp.Router router) {
        this.router = router;
    }

    @Override
    public void itemClicked(Item item) {
        router.showItemInfo(item);
    }
}