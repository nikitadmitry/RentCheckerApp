package by.slowar.rentchecker.di.modules;

import android.content.Context;

import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 05.12.2019.
 */

@Module
public class LocalStorageModule {

    @ApplicationScope
    @Provides
    ParametersPreferences providePreferences(Context context) {
        return new ParametersPreferences(context);
    }
}