package by.slowar.rentchecker.data.items.loaders;

import by.slowar.rentchecker.data.items.ItemsDataLoader;

/**
 * Created by SlowAR on 17.12.2019.
 */

public interface DataLoader {

    void loadSiteData(ItemsDataLoader.OnSiteDataLoadedListener listener);
}