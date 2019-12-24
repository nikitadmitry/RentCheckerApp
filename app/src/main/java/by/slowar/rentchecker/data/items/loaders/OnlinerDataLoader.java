package by.slowar.rentchecker.data.items.loaders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.slowar.rentchecker.App;
import by.slowar.rentchecker.data.model.ItemLocation;
import by.slowar.rentchecker.data.items.ItemsDataLoader;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.items.onliner.OnlinerItemsPojo;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.ParametersObject;
import by.slowar.rentchecker.data.remote.OnlinerPageSourceApi;
import by.slowar.rentchecker.util.SchedulersUtil;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by SlowAR on 17.12.2019.
 */

public class OnlinerDataLoader implements DataLoader {

    private OnlinerPageSourceApi onlinerPageSourceApi;
    private ParametersPreferences parametersPreferences;
    private SchedulersUtil schedulersUtil;

    public OnlinerDataLoader(ParametersPreferences parametersPreferences, SchedulersUtil schedulersUtil) {
        onlinerPageSourceApi = App.getAppComponent().getOnlinerGsonPageSourceApi();
        this.parametersPreferences = parametersPreferences;
        this.schedulersUtil = schedulersUtil;
    }

    @Override
    public void loadSiteData(ItemsDataLoader.OnSiteDataLoadedListener listener) {
        ParametersObject params = parametersPreferences.getParameters();

        List<String> roomsList = new ArrayList<>();
        Map<ParametersPreferences.RoomType, Boolean> roomsMap = params.getRoomsMap();
        for (ParametersPreferences.RoomType value : ParametersPreferences.RoomType.values()) {
            Boolean isChecked = roomsMap.get(value);
            if (isChecked != null && isChecked) {
                int valueOrdinal = (value.ordinal() + 1);
                roomsList.add(valueOrdinal + "_room" + (valueOrdinal > 1 ? "s" : ""));
            }
        }
        String[] rooms = new String[roomsList.size()];
        rooms = roomsList.toArray(rooms);

        ItemLocation cityLocation = ItemLocation.getConstantLocation(ItemLocation.City.valueOf(parametersPreferences.getCity()));
        int priceMin = params.getMinPrice();
        int priceMax = params.getMaxPrice();
        boolean isOwner = params.isOwner();

        float coordinatesOffset = 0.5f;
        onlinerPageSourceApi.pageSource(priceMin, priceMax, isOwner,
                cityLocation.getLatitude(), cityLocation.getLongitude(),
                cityLocation.getLatitude() + coordinatesOffset, cityLocation.getLongitude() + coordinatesOffset,
                rooms)
                .observeOn(schedulersUtil.getAndroidMainThreadScheduler())
                .subscribeOn(schedulersUtil.getIoScheduler())
                .subscribe(new SingleObserver<OnlinerItemsPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(OnlinerItemsPojo itemsPojo) {
                        listener.onSiteDataLoaded(itemsPojo, RentItemsHelper.Site.Onliner);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}