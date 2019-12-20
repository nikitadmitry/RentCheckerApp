package by.slowar.rentchecker.data.items;

import by.slowar.rentchecker.data.items.parsers.ItemParser;
import by.slowar.rentchecker.data.items.parsers.ItemParserFactory;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.util.SchedulersUtil;

/**
 * Created by SlowAR on 06.12.2019.
 */

public class ItemsDataParser {

    private ParametersPreferences params;
    private SchedulersUtil schedulersUtil;
    private ItemParserFactory itemParserFactory;

    public ItemsDataParser(ParametersPreferences params, SchedulersUtil schedulersUtil, ItemParserFactory itemParserFactory) {
        this.params = params;
        this.schedulersUtil = schedulersUtil;
        this.itemParserFactory = itemParserFactory;
    }

    public void parseData(ItemsPojo itemsPojo, OnParsedItemListener listener, RentItemsHelper.Site site) {
        ItemParser itemParser = itemParserFactory.getItemParser(site, params, schedulersUtil, listener);
        itemParser.setItemsData(itemsPojo);
        itemParser.parseItems();
    }

    public interface OnParsedItemListener {

        void onParsedListener(Item item);
    }
}