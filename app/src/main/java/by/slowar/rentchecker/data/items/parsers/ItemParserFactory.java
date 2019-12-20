package by.slowar.rentchecker.data.items.parsers;

import by.slowar.rentchecker.data.items.ItemsDataParser;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.util.SchedulersUtil;

/**
 * Created by SlowAR on 19.12.2019.
 */

public class ItemParserFactory {

    private ItemParser onlinerParser;

    public ItemParser getItemParser(RentItemsHelper.Site site, ParametersPreferences params, SchedulersUtil schedulersUtil,
                                    ItemsDataParser.OnParsedItemListener listener) {
        switch (site) {
            case Onliner:
                if (onlinerParser == null) {
                    onlinerParser = new OnlinerItemParser(params, schedulersUtil, listener);
                }
                return onlinerParser;
            default:
                throw new IllegalStateException("Site is not supported by application");
        }
    }
}