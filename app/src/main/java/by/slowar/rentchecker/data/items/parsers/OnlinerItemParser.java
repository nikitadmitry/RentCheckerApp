package by.slowar.rentchecker.data.items.parsers;

import android.util.Patterns;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import by.slowar.rentchecker.App;
import by.slowar.rentchecker.data.items.ItemLocation;
import by.slowar.rentchecker.data.items.ItemsPojo;
import by.slowar.rentchecker.data.items.onliner.Apartment;
import by.slowar.rentchecker.data.items.onliner.Location;
import by.slowar.rentchecker.data.items.onliner.OnlinerItemsPojo;
import by.slowar.rentchecker.data.items.onliner.Price;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.data.remote.OnlinerPageSourceApi;
import by.slowar.rentchecker.util.SchedulersUtil;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static by.slowar.rentchecker.data.items.ItemsDataParser.OnParsedItemListener;

/**
 * Created by SlowAR on 09.12.2019.
 */

public class OnlinerItemParser implements ItemParser {

    private ParametersPreferences params;
    private SchedulersUtil schedulersUtil;
    private OnlinerItemsPojo onlinerItemsPojo;
    private OnParsedItemListener listener;
    private OnlinerPageSourceApi onlinerPageSourceApi;

    public OnlinerItemParser(ParametersPreferences parametersPreferences, SchedulersUtil schedulersUtil, OnParsedItemListener listener) {
        this.params = parametersPreferences;
        this.schedulersUtil = schedulersUtil;
        this.listener = listener;
        onlinerPageSourceApi = App.getAppComponent().getOnlinerScalarsPageSourceApi();
    }

    @Override
    public void setItemsData(ItemsPojo itemsPojo) {
        this.onlinerItemsPojo = (OnlinerItemsPojo) itemsPojo;
    }

    @Override
    public void parseItems() {
        for (final Apartment apartment : onlinerItemsPojo.getApartments()) {
            onlinerPageSourceApi.pageSourceInfo(apartment.getId())
                    .subscribeOn(schedulersUtil.getIoScheduler())
                    .observeOn(schedulersUtil.getAndroidMainThreadScheduler())
                    .subscribe(new SingleObserver<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onSuccess(String s) {
                            createItemObject(apartment, s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private void createItemObject(Apartment apartment, String data) {
        Document itemDocument = Jsoup.parse(data);

        Elements phoneElements = itemDocument.select("li.apartment-info__item.apartment-info__item_secondary");
        String phone = phoneElements.select("a").text();

        Elements ownerElements = itemDocument.select("div.apartment-info__sub-line.apartment-info__sub-line_extended");
        String owner = ownerElements.select("a").text();
        owner = owner.substring(0, owner.indexOf(" "));

        List<String> photosUrls = new ArrayList<>();
        Elements photoElements = itemDocument.select("div.apartment-cover__thumbnails-inner").select("div");
        for (Element element : photoElements) {
            String attr = element.attr("style");
            Matcher matcher = Patterns.WEB_URL.matcher(attr);
            if (matcher.find()) {
                String url = matcher.group();
                url = url.substring(0, url.length() - 1);
                photosUrls.add(url);
            }
        }

        Location location = apartment.getLocation();
        ItemLocation itemLocation = new ItemLocation(location.getLatitude(), location.getLongitude());
        Price price = apartment.getPrice();
        float priceUsd = Float.parseFloat(price.getAmount());
        ParametersPreferences.RoomType roomType = ParametersPreferences.RoomType.values()[Integer.parseInt(apartment.getRentType().substring(0, 1)) - 1];

        Item item = new Item(location.getAddress(), phone, priceUsd, roomType, owner, params.isOwner(), photosUrls, itemLocation);
        listener.onParsedListener(item);
    }
}