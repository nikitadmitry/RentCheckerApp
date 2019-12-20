package by.slowar.rentchecker.di.components;

import by.slowar.rentchecker.data.remote.OnlinerPageSourceApi;
import by.slowar.rentchecker.di.modules.AppModule;
import by.slowar.rentchecker.di.modules.LocalStorageModule;
import by.slowar.rentchecker.di.modules.PresenterModule;
import by.slowar.rentchecker.di.modules.RemoteStorageModule;
import by.slowar.rentchecker.di.modules.RouterModule;
import by.slowar.rentchecker.di.modules.UtilsModule;
import by.slowar.rentchecker.di.qualifiers.OnlinerGsonQualifier;
import by.slowar.rentchecker.di.qualifiers.OnlinerScalarsQualifier;
import by.slowar.rentchecker.di.scopes.ApplicationScope;
import by.slowar.rentchecker.ui.check.service.CheckService;
import dagger.Component;

/**
 * Created by SlowAR on 03.12.2019.
 */

@ApplicationScope
@Component(modules = {AppModule.class, LocalStorageModule.class, RemoteStorageModule.class,
        PresenterModule.class, UtilsModule.class})
public interface AppComponent {

    ScreenComponent getScreenComponent(RouterModule routerModule);

    @OnlinerGsonQualifier
    OnlinerPageSourceApi getOnlinerGsonPageSourceApi();

    @OnlinerScalarsQualifier
    OnlinerPageSourceApi getOnlinerScalarsPageSourceApi();

    void inject(CheckService checkService);
}