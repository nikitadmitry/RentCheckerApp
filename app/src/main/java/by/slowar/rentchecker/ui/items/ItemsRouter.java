package by.slowar.rentchecker.ui.items;

import android.content.Context;
import android.content.Intent;

import by.slowar.rentchecker.common.BaseRouter;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.ui.items.info.ItemInfoActivity;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class ItemsRouter extends BaseRouter implements ItemsMvp.Router {

    public ItemsRouter(Context context) {
        super(context);
    }

    @Override
    public void showItemInfo(Item item) {
        Intent intent = ItemInfoActivity.newIntent(getContext(), item);
        getContext().startActivity(intent);
    }
}