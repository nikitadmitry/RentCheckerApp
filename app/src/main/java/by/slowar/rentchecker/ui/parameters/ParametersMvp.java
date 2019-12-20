package by.slowar.rentchecker.ui.parameters;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.mvp.view.MvpView;
import by.slowar.rentchecker.data.items.ItemLocation;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.ParametersObject;

/**
 * Created by SlowAR on 05.12.2019.
 */

public final class ParametersMvp {

    private ParametersMvp() {
    }

    public interface View extends MvpView {

        void finishActivity();

        void setParametersValues(ParametersObject parameters);
    }

    public interface Presenter extends MvpPresenter<ParametersMvp.View> {

        void viewIsReady();

        void applyButtonClicked();

        void minPriceChanged(int minPrice);

        void maxPriceChanged(int maxPrice);

        void roomChanged(ParametersPreferences.RoomType roomType, boolean isChecked);

        void ownerChanged(boolean isChecked);

        void cityChanged(ItemLocation.City city);

        void siteChanged(RentItemsHelper.Site site, boolean isChecked);
    }
}