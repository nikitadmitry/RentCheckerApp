package by.slowar.rentchecker.ui.check.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Set;

import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 13.12.2019.
 */

public class CheckServiceBroadcastReceiver extends BroadcastReceiver {

    public enum Status {
        StopService,
        NewItem
    }

    public static final String CHECK_SERVICE_ACTION = "by.slowar.rentchecker.checkservicebroadcast";

    private CheckServiceListener listener;

    public CheckServiceBroadcastReceiver(CheckServiceListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean statusValue = intent.getBooleanExtra(Status.StopService.name(), false);
        if (statusValue) {
            listener.onStopService();
        } else {
            @SuppressWarnings("unchecked")
            Set<Item> allItems = (Set<Item>) intent.getSerializableExtra(Status.NewItem.name());
            listener.addNewItems(allItems);
        }
    }

    public void unregister() {
        listener = null;
    }

    public interface CheckServiceListener {

        void onStopService();

        void addNewItems(Set<Item> items);
    }
}