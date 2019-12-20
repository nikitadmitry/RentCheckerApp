package by.slowar.rentchecker.di.modules;

import by.slowar.rentchecker.data.items.ItemsDataLoader;
import by.slowar.rentchecker.data.items.ItemsDataParser;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.items.parsers.ItemParserFactory;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.di.scopes.ApplicationScope;
import by.slowar.rentchecker.util.SchedulersUtil;
import dagger.Module;
import dagger.Provides;

/**
 * Created by SlowAR on 05.12.2019.
 */

@Module
public class UtilsModule {

    @ApplicationScope
    @Provides
    SchedulersUtil provideSchedulersUtil() {
        return new SchedulersUtil();
    }

    @ApplicationScope
    @Provides
    RentItemsHelper provideRentItemsHelper(ItemsDataLoader itemsDataLoader, ItemsDataParser itemsDataParser,
                                           ParametersPreferences params, SchedulersUtil schedulersUtil) {
        return new RentItemsHelper(itemsDataLoader, itemsDataParser, params, schedulersUtil);
    }

    @ApplicationScope
    @Provides
    ItemsDataParser provideItemsDataParser(ParametersPreferences params, SchedulersUtil schedulersUtil, ItemParserFactory itemParserFactory) {
        return new ItemsDataParser(params, schedulersUtil, itemParserFactory);
    }

    @ApplicationScope
    @Provides
    ItemParserFactory provideItemParserFactory() {
        return new ItemParserFactory();
    }
}