package by.slowar.rentchecker.di.components;

import by.slowar.rentchecker.di.modules.PresenterModule;
import by.slowar.rentchecker.di.modules.RouterModule;
import by.slowar.rentchecker.di.scopes.ScreenScope;
import by.slowar.rentchecker.ui.check.CheckActivity;
import by.slowar.rentchecker.ui.check.service.CheckService;
import by.slowar.rentchecker.ui.items.ItemsFragment;
import by.slowar.rentchecker.ui.items.info.ItemInfoActivity;
import by.slowar.rentchecker.ui.map.MapFragment;
import by.slowar.rentchecker.ui.parameters.ParametersActivity;
import dagger.Subcomponent;

/**
 * Created by SlowAR on 04.12.2019.
 */

@ScreenScope
@Subcomponent(modules = {PresenterModule.class, RouterModule.class})
public interface ScreenComponent {

    void inject(CheckActivity checkActivity);

    void inject(ItemsFragment itemsFragment);

    void inject(ItemInfoActivity itemInfoActivity);

    void inject(MapFragment mapFragment);

    void inject(ParametersActivity parametersActivity);

    void inject(CheckService checkService);
}
