package by.slowar.rentchecker.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import by.slowar.rentchecker.common.Constants;
import by.slowar.rentchecker.data.model.ItemLocation;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.model.ParametersObject;

/**
 * Created by SlowAR on 05.12.2019.
 */

public class ParametersPreferences {

    private final String FILE_NAME = "parameters";

    public enum Parameters {
        MinPrice,
        MaxPrice,
        Owner,
        City
    }

    public enum RoomType {
        Room1,
        Room2,
        Room3,
        Room4
    }

    private SharedPreferences preferences;

    public ParametersPreferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setPrice(Parameters parameter, int minPrice) {
        getEditor().putInt(parameter.name(), minPrice).commit();
    }

    public void setRoom(RoomType roomType, boolean roomChecked) {
        getEditor().putBoolean(roomType.name(), roomChecked).commit();
    }

    public void setOwner(boolean ownerChecked) {
        getEditor().putBoolean(Parameters.Owner.name(), ownerChecked).commit();
    }

    public void setCity(ItemLocation.City city) {
        getEditor().putString(Parameters.City.name(), city.name()).commit();
    }

    public void setSite(RentItemsHelper.Site site, boolean isChecked) {
        getEditor().putBoolean(site.name(), isChecked).commit();
    }

    public int getMinPrice() {
        return preferences.getInt(Parameters.MinPrice.name(), Constants.MIN_PRICE);
    }

    public int getMaxPrice() {
        return preferences.getInt(Parameters.MaxPrice.name(), Constants.MAX_PRICE);
    }

    public boolean getRoom(RoomType roomType) {
        return preferences.getBoolean(roomType.name(), false);
    }

    public boolean isOwner() {
        return preferences.getBoolean(Parameters.Owner.name(), false);
    }

    public String getCity() {
        return preferences.getString(Parameters.City.name(), ItemLocation.City.Minsk.name());
    }

    public boolean getSite(RentItemsHelper.Site site) {
        return preferences.getBoolean(site.name(), false);
    }

    public ParametersObject getParameters() {
        ParametersObject parameters = new ParametersObject();
        parameters.setMinPrice(getMinPrice());
        parameters.setMaxPrice(getMaxPrice());
        for (RoomType value : RoomType.values()) {
            parameters.putRoom(value, getRoom(value));
        }
        parameters.setOwner(isOwner());
        parameters.setCity(ItemLocation.City.valueOf(getCity()));
        for (RentItemsHelper.Site value : RentItemsHelper.Site.values()) {
            parameters.putSite(value, getSite(value));
        }

        return parameters;
    }
}