package by.slowar.rentchecker.data.model;

import java.io.Serializable;
import java.util.List;

import static by.slowar.rentchecker.data.local.ParametersPreferences.RoomType;

/**
 * Created by SlowAR on 03.12.2019.
 */

public class Item implements Serializable, Comparable<Item> {

    private String address;
    private String phone;
    private float price;
    private RoomType roomType;
    private String ownerName;
    private boolean isOwner;
    private List<String> photoUrlList;
    private ItemLocation location;

    public Item(String address, String phone, float price, RoomType roomType, String ownerName, boolean isOwner,
                List<String> photoUrlList, ItemLocation location) {
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.roomType = roomType;
        this.ownerName = ownerName;
        this.isOwner = isOwner;
        this.photoUrlList = photoUrlList;
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public float getPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public ItemLocation getLocation() {
        return location;
    }

    public double getLocationLatitude() {
        return location.getLatitude();
    }

    public double getLocationLongitude() {
        return location.getLongitude();
    }

    @Override
    public int compareTo(Item o) {
        return getAddress().compareTo(o.getAddress());
    }
}