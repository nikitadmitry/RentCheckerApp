package by.slowar.rentchecker.data.remote;

import by.slowar.rentchecker.data.items.onliner.OnlinerItemsPojo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by SlowAR on 08.12.2019.
 */

public interface OnlinerPageSourceApi {

    @Headers({"Accept: application/json"})
    @GET("apartments?currency=usd&order=created_at%3Adesc&page=1")
    Single<OnlinerItemsPojo> pageSource(@Query("price[min]") int priceMin, @Query("price[max]") int priceMax,
                                        @Query("bounds[lb][lat]") double lblat,
                                        @Query("bounds[lb][long]") double lblong, @Query("bounds[rt][lat]") double rtlat,
                                        @Query("bounds[rt][long]") double rtlong, @Query("rent_type[]") String... rooms);

    @Headers({"Accept: application/json"})
    @GET("apartments/{id}")
    Single<String> pageSourceInfo(@Path("id") int id);
}