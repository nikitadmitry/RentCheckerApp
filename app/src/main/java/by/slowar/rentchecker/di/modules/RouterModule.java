package by.slowar.rentchecker.di.modules;

import android.content.Context;

import by.slowar.rentchecker.common.RouterStub;
import by.slowar.rentchecker.di.scopes.ScreenScope;
import by.slowar.rentchecker.ui.check.CheckMvp;
import by.slowar.rentchecker.ui.check.CheckRouter;
import by.slowar.rentchecker.ui.items.ItemsMvp;
import by.slowar.rentchecker.ui.items.ItemsRouter;
import by.slowar.rentchecker.ui.items.info.ItemInfoMvp;
import by.slowar.rentchecker.ui.items.info.ItemInfoRouter;
import by.slowar.rentchecker.ui.map.MapMvp;
import by.slowar.rentchecker.ui.map.MapRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 04.12.2019.
 */

@Module
public class RouterModule {

    private Context context;

    public RouterModule(Context context) {
        this.context = context;
    }

    @ScreenScope
    @Provides
    CheckMvp.Router provideCheckRouter() {
        return new CheckRouter(context);
    }

    @ScreenScope
    @Provides
    ItemsMvp.Router provideItemsRouter() {
        return new ItemsRouter(context);
    }

    @ScreenScope
    @Provides
    ItemInfoMvp.Router provideItemInfoRouter() {
        return new ItemInfoRouter(context);
    }

    @ScreenScope
    @Provides
    MapMvp.Router provideMapRouter() {
        return new MapRouter(context);
    }

    @ScreenScope
    @Provides
    RouterStub provideRouterStub() {
        return new RouterStub(context);
    }
}