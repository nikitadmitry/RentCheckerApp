package by.slowar.rentchecker.data.items;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import by.slowar.rentchecker.common.Constants;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.util.SchedulersUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static by.slowar.rentchecker.data.local.ParametersPreferences.RoomType;

/**
 * Created by SlowAR on 06.12.2019.
 */

public class RentItemsHelper implements ItemsDataLoader.OnSiteDataLoadedListener, ItemsDataParser.OnParsedItemListener {

    public enum Site {
        Onliner
    }

    private ItemsDataLoader itemsDataLoader;
    private ItemsDataParser itemsDataParser;
    private ParametersPreferences params;
    private SchedulersUtil schedulersUtil;
    private Set<OnNewItemListener> listeners;
    private Disposable cooldownDisposable;
    private Set<Item> allItems;

    public RentItemsHelper(ItemsDataLoader itemsDataLoader, ItemsDataParser itemsDataParser,
                           ParametersPreferences parametersPreferences, SchedulersUtil schedulersUtil) {
        this.itemsDataLoader = itemsDataLoader;
        this.itemsDataParser = itemsDataParser;
        this.params = parametersPreferences;
        this.schedulersUtil = schedulersUtil;
        listeners = new HashSet<>();
        allItems = new TreeSet<>();
    }

    public void subscribeOnNewItems(OnNewItemListener listener) {
        if (listeners.isEmpty()) {
            launchRentItemsChecker();
        }
        listeners.add(listener);
    }

    private void launchRentItemsChecker() {
        itemsDataLoader.loadSitesData(RentItemsHelper.this);

        Observable<Long> rentRefreshObservable = Observable.interval(Constants.REFRESH_COOL_DOWN_SEC, TimeUnit.SECONDS)
                .subscribeOn(schedulersUtil.getIoScheduler())
                .observeOn(schedulersUtil.getAndroidMainThreadScheduler())
                .repeat();

        cooldownDisposable = rentRefreshObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                itemsDataLoader.loadSitesData(RentItemsHelper.this);
            }
        });
    }

    public void updateDataToCurrentParameters() {
        filterAllFoundItems();
        itemsDataLoader.updateDataToCurrentParameters();
    }

    private void filterAllFoundItems() {
        Iterator<Item> iterator = allItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            boolean price = item.getPrice() <= params.getMaxPrice() && item.getPrice() >= params.getMinPrice();
            boolean room = false;
            for (RoomType value : RoomType.values()) {
                if (params.getRoom(value) && item.getRoomType() == value) {
                    room = true;
                    break;
                }
            }

            boolean isRemains = price && room;
            if (!isRemains) {
                iterator.remove();
            }
        }
    }

    public void unsubscribeFromNewItems(OnNewItemListener listener) {
        listeners.remove(listener);
        if (listeners.isEmpty() && cooldownDisposable != null) {
            cooldownDisposable.dispose();
        }
    }

    public void dispose() {
        listeners.clear();
        if (cooldownDisposable != null) {
            cooldownDisposable.dispose();
        }
        itemsDataLoader.dispose();
    }

    @Override
    public void onSiteDataLoaded(ItemsPojo itemsPojo, Site site) {
        itemsDataParser.parseData(itemsPojo, this, site);
    }

    @Override
    public void onParsedListener(Item item) {
        boolean isJustAdded = allItems.add(item);
        for (OnNewItemListener listener : listeners) {
            listener.onNewItems(allItems, isJustAdded);
        }
    }

    public Set<Item> getAllItems() {
        return allItems;
    }

    public interface OnNewItemListener {

        void onNewItems(Set<Item> items, boolean isJustAdded);
    }
}