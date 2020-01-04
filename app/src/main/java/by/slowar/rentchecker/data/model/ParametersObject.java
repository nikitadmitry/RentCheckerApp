package by.slowar.rentchecker.data.model;

import java.util.HashMap;
import java.util.Map;

import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;

/**
 * Created by SlowAR on 05.12.2019.
 */

public class ParametersObject {

    private int minPrice;
    private int maxPrice;
    private Map<ParametersPreferences.RoomType, Boolean> roomsMap;
    private ItemLocation.City city;
    private Map<RentItemsHelper.Site, Boolean> sitesMap;

    public ParametersObject() {
        roomsMap = new HashMap<>();
        sitesMap = new HashMap<>();
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void putRoom(ParametersPreferences.RoomType roomType, boolean isChecked) {
        roomsMap.put(roomType, isChecked);
    }

    public Map<ParametersPreferences.RoomType, Boolean> getRoomsMap() {
        return roomsMap;
    }

    public ItemLocation.City getCity() {
        return city;
    }

    public void setCity(ItemLocation.City city) {
        this.city = city;
    }

    public void putSite(RentItemsHelper.Site site, boolean isChecked) {
        sitesMap.put(site, isChecked);
    }

    public Map<RentItemsHelper.Site, Boolean> getSitesMap() {
        return sitesMap;
    }
}