package by.slowar.rentchecker.data.items.parsers;

import by.slowar.rentchecker.data.items.ItemsPojo;

/**
 * Created by SlowAR on 17.12.2019.
 */

public interface ItemParser {

    void setItemsData(ItemsPojo itemsPojo);

    void parseItems();
}