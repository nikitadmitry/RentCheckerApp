package by.slowar.rentchecker;

import androidx.multidex.MultiDexApplication;

import by.slowar.rentchecker.di.components.AppComponent;
import by.slowar.rentchecker.di.components.DaggerAppComponent;
import by.slowar.rentchecker.di.modules.AppModule;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class App extends MultiDexApplication {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}