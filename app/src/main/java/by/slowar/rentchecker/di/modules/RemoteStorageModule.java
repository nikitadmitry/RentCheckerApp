package by.slowar.rentchecker.di.modules;

import by.slowar.rentchecker.data.items.ItemsDataLoader;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.remote.OnlinerPageSourceApi;
import by.slowar.rentchecker.di.qualifiers.OnlinerGsonQualifier;
import by.slowar.rentchecker.di.qualifiers.OnlinerScalarsQualifier;
import by.slowar.rentchecker.di.scopes.ApplicationScope;
import by.slowar.rentchecker.util.SchedulersUtil;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by SlowAR on 05.12.2019.
 */

@Module
public class RemoteStorageModule {

    @ApplicationScope
    @OnlinerGsonQualifier
    @Provides
    OnlinerPageSourceApi provideGsonOnlinerApi() {
        Retrofit onlinerRetrofit = new Retrofit.Builder()
                .baseUrl("https://pk.api.onliner.by/search/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return onlinerRetrofit.create(OnlinerPageSourceApi.class);
    }

    @ApplicationScope
    @OnlinerScalarsQualifier
    @Provides
    OnlinerPageSourceApi provideScalarsOnlinerApi() {
        Retrofit onlinerRetrofit = new Retrofit.Builder()
                .baseUrl("https://r.onliner.by/pk/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return onlinerRetrofit.create(OnlinerPageSourceApi.class);
    }

    @ApplicationScope
    @Provides
    ItemsDataLoader provideItemsDataLoader(ParametersPreferences parametersPreferences, SchedulersUtil schedulersUtil) {
        return new ItemsDataLoader(parametersPreferences, schedulersUtil);
    }
}