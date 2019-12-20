package by.slowar.rentchecker.di.modules;

import by.slowar.rentchecker.common.RouterStub;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.di.scopes.ApplicationScope;
import by.slowar.rentchecker.di.scopes.ScreenScope;
import by.slowar.rentchecker.ui.check.CheckMvp;
import by.slowar.rentchecker.ui.check.CheckPresenter;
import by.slowar.rentchecker.ui.check.service.CheckServiceMvp;
import by.slowar.rentchecker.ui.check.service.CheckServicePresenter;
import by.slowar.rentchecker.ui.items.ItemsMvp;
import by.slowar.rentchecker.ui.items.ItemsPresenter;
import by.slowar.rentchecker.ui.items.info.ItemInfoMvp;
import by.slowar.rentchecker.ui.items.info.ItemInfoPresenter;
import by.slowar.rentchecker.ui.map.MapMvp;
import by.slowar.rentchecker.ui.map.MapPresenter;
import by.slowar.rentchecker.ui.parameters.ParametersMvp;
import by.slowar.rentchecker.ui.parameters.ParametersPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 03.12.2019.
 */

@Module
public class PresenterModule {

    @ScreenScope
    @Provides
    CheckMvp.Presenter provideCheckPresenter(CheckMvp.Router router) {
        return new CheckPresenter(router);
    }

    @ScreenScope
    @Provides
    ItemsMvp.Presenter provideItemsPresenter(ItemsMvp.Router router) {
        return new ItemsPresenter(router);
    }

    @ScreenScope
    @Provides
    ItemInfoMvp.Presenter provideItemInfoPresenter(ItemInfoMvp.Router router) {
        return new ItemInfoPresenter(router);
    }

    @ScreenScope
    @Provides
    MapMvp.Presenter provideMapPresenter(MapMvp.Router router) {
        return new MapPresenter(router);
    }

    @ScreenScope
    @Provides
    ParametersMvp.Presenter provideParametersPresenter(ParametersPreferences parametersPreferences, RouterStub routerStub) {
        return new ParametersPresenter(parametersPreferences, routerStub);
    }

    @ApplicationScope
    @Provides
    CheckServiceMvp.Presenter provideCheckServicePresenter(RentItemsHelper rentItemsHelper) {
        return new CheckServicePresenter(rentItemsHelper);
    }
}