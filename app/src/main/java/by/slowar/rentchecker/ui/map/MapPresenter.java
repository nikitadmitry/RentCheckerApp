package by.slowar.rentchecker.ui.map;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 04.12.2019.
 */

public class MapPresenter extends BasePresenter<MapMvp.View> implements MapMvp.Presenter {

    private MapMvp.Router router;

    @Inject
    public MapPresenter(MapMvp.Router router) {
        this.router = router;
    }

    @Override
    public void itemClicked(Item item) {
        router.showItemInfo(item);
    }
}