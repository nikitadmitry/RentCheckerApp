package by.slowar.rentchecker.ui.check.service;

import java.util.Set;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 14.12.2019.
 */

public class CheckServicePresenter extends BasePresenter<CheckServiceMvp.View> implements CheckServiceMvp.Presenter,
        RentItemsHelper.OnNewItemListener {

    private RentItemsHelper rentItemsHelper;

    @Inject
    public CheckServicePresenter(RentItemsHelper rentItemsHelper) {
        this.rentItemsHelper = rentItemsHelper;
    }

    @Override
    public void onServiceReady() {
        rentItemsHelper.subscribeOnNewItems(this);
    }

    @Override
    public Set<Item> onItemsRequest() {
        return rentItemsHelper.getAllItems();
    }

    @Override
    public void onParametersChanged() {
        rentItemsHelper.updateDataToCurrentParameters();
    }

    @Override
    public void onDestroy() {
        rentItemsHelper.dispose();
    }

    @Override
    public void onNewItems(Set<Item> items, boolean isJustAdded) {
        getView().sendNewItems(items);
        if (isJustAdded) {
            getView().notifyNewItem();
        }
    }
}