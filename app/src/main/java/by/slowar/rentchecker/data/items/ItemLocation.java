package by.slowar.rentchecker.data.items;

import java.io.Serializable;

/**
 * Created by SlowAR on 10.12.2019.
 */

public class ItemLocation implements Serializable {

    public enum City {
        Minsk,
    }

    private static final ItemLocation MINSK = new ItemLocation(53.7, 27.2);

    private Double latitude;
    private Double longitude;

    public ItemLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public static ItemLocation getConstantLocation(City city) {
        switch (city) {
            case Minsk:
                return MINSK;
            default:
                throw new IllegalStateException("No such city type " + city.name());
        }
    }
}