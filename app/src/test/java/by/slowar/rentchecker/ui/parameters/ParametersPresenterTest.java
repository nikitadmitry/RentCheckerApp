package by.slowar.rentchecker.ui.parameters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import by.slowar.rentchecker.common.RouterStub;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.ItemLocation;
import by.slowar.rentchecker.data.model.ParametersObject;

/**
 * Created by SlowAR on 22.12.2019.
 */

public class ParametersPresenterTest {

    private ParametersMvp.Presenter presenter;

    @Mock
    ParametersPreferences mockParametersPreferences;

    @Mock
    ParametersMvp.View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RouterStub mockRouterStub = Mockito.mock(RouterStub.class);
        presenter = new ParametersPresenter(mockParametersPreferences, mockRouterStub);
        presenter.attachView(mockView);
    }

    @Test
    public void applyButtonClicked() {
        presenter.applyButtonClicked();
        Mockito.verify(mockView).finishActivity();
    }

    @Test
    public void minPriceChanged() {
        int minPrice = 5;
        presenter.minPriceChanged(minPrice);
        Mockito.verify(mockParametersPreferences).setPrice(ParametersPreferences.Parameters.MinPrice, minPrice);
    }

    @Test
    public void maxPriceChanged() {
        int maxPrice = 5;
        presenter.maxPriceChanged(maxPrice);
        Mockito.verify(mockParametersPreferences).setPrice(ParametersPreferences.Parameters.MaxPrice, maxPrice);
    }

    @Test
    public void roomChanged() {
        ParametersPreferences.RoomType roomType = ParametersPreferences.RoomType.Room2;
        boolean isChecked = true;
        presenter.roomChanged(roomType, isChecked);
        Mockito.verify(mockParametersPreferences).setRoom(roomType, isChecked);
    }

    @Test
    public void ownerChanged() {
        boolean isChecked = true;
        presenter.ownerChanged(isChecked);
        Mockito.verify(mockParametersPreferences).setOwner(isChecked);
    }

    @Test
    public void cityChanged() {
        ItemLocation.City city = ItemLocation.City.Minsk;
        presenter.cityChanged(city);
        Mockito.verify(mockParametersPreferences).setCity(city);
    }

    @Test
    public void siteChanged() {
        RentItemsHelper.Site siteType = RentItemsHelper.Site.Onliner;
        boolean isChecked = true;
        presenter.siteChanged(siteType, isChecked);
        Mockito.verify(mockParametersPreferences).setSite(siteType, isChecked);
    }

    @Test
    public void viewIsReady() {
        presenter.viewIsReady();
        ParametersObject object = Mockito.verify(mockParametersPreferences).getParameters();
        Mockito.verify(mockView).setParametersValues(object);
    }
}