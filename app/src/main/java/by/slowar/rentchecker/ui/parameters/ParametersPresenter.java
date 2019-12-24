package by.slowar.rentchecker.ui.parameters;

import javax.inject.Inject;

import by.slowar.mvp.presenter.BasePresenter;
import by.slowar.rentchecker.common.RouterStub;
import by.slowar.rentchecker.data.model.ItemLocation;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.ParametersObject;

/**
 * Created by SlowAR on 05.12.2019.
 */

public class ParametersPresenter extends BasePresenter<ParametersMvp.View> implements ParametersMvp.Presenter {

    private ParametersPreferences parametersPreferences;
    private RouterStub router;

    @Inject
    public ParametersPresenter(ParametersPreferences parametersPreferences, RouterStub router) {
        this.parametersPreferences = parametersPreferences;
        this.router = router;
    }

    @Override
    public void applyButtonClicked() {
        getView().finishActivity();
    }

    @Override
    public void minPriceChanged(int minPrice) {
        parametersPreferences.setPrice(ParametersPreferences.Parameters.MinPrice, minPrice);
    }

    @Override
    public void maxPriceChanged(int maxPrice) {
        parametersPreferences.setPrice(ParametersPreferences.Parameters.MaxPrice, maxPrice);
    }

    @Override
    public void roomChanged(ParametersPreferences.RoomType roomType, boolean isChecked) {
        parametersPreferences.setRoom(roomType, isChecked);
    }

    @Override
    public void ownerChanged(boolean isChecked) {
        parametersPreferences.setOwner(isChecked);
    }

    @Override
    public void cityChanged(ItemLocation.City city) {
        parametersPreferences.setCity(city);
    }

    @Override
    public void siteChanged(RentItemsHelper.Site site, boolean isChecked) {
        parametersPreferences.setSite(site, isChecked);
    }

    @Override
    public void viewIsReady() {
        ParametersObject parametersObject = parametersPreferences.getParameters();
        getView().setParametersValues(parametersObject);
    }
}