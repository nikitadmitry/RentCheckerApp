package by.slowar.rentchecker.di.modules;

import android.content.Context;

import by.slowar.rentchecker.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 04.12.2019.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    Context provideContext() {
        return context;
    }
}