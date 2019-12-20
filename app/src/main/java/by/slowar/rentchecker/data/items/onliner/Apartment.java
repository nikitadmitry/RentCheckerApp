
package by.slowar.rentchecker.data.items.onliner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apartment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("rent_type")
    @Expose
    private String rentType;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("last_time_up")
    @Expose
    private String lastTimeUp;
    @SerializedName("up_available_in")
    @Expose
    private Integer upAvailableIn;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastTimeUp() {
        return lastTimeUp;
    }

    public void setLastTimeUp(String lastTimeUp) {
        this.lastTimeUp = lastTimeUp;
    }

    public Integer getUpAvailableIn() {
        return upAvailableIn;
    }

    public void setUpAvailableIn(Integer upAvailableIn) {
        this.upAvailableIn = upAvailableIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
