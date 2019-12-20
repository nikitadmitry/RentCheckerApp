package by.slowar.rentchecker.ui.check.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.mvp.MvpService;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.ui.check.CheckActivity;

/**
 * Created by SlowAR on 12.12.2019.
 */

public class CheckService extends MvpService<CheckServiceMvp.View> implements CheckServiceMvp.View {

    public static final String CHANNEL_ID = "CheckService";

    @Inject
    public CheckServicePresenter presenter;
    private CheckBinder binder;
    private NotificationManager notificationManager;
    private Notification notification;
    private PendingIntent notificationPendingIntent;
    private PendingIntent stopPendingIntent;
    private int newItemsCount;

    @Override
    public void onCreate() {
        App.getAppComponent().inject(this);
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        binder = new CheckBinder();
        createNotificationsChannel();
    }

    private void createNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID,
                    getString(R.string.check_service), NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleMessages(intent);
        notification = createNotification();
        launchService(notification);
        return START_NOT_STICKY;
    }

    private void handleMessages(Intent intent) {
        tryToStopService(intent);
        itemsListRequest(intent);
    }

    private void tryToStopService(Intent intent) {
        boolean isNeedToStopService = intent.getBooleanExtra(CheckServiceBroadcastReceiver.Status.StopService.name(), false);
        if (isNeedToStopService) {
            Intent stopIntent = new Intent();
            stopIntent.setAction(CheckServiceBroadcastReceiver.CHECK_SERVICE_ACTION);
            stopIntent.putExtra(CheckServiceBroadcastReceiver.Status.StopService.name(), true);
            sendBroadcast(stopIntent);
            stopSelf();
        }
    }

    private void itemsListRequest(Intent intent) {
        boolean isNeedToGetItems = intent.getBooleanExtra(CheckServiceBroadcastReceiver.Status.NewItem.name(), false);
        if (isNeedToGetItems) {
            Set<Item> allItems = presenter.onItemsRequest();
            Intent itemsIntent = new Intent();
            itemsIntent.setAction(CheckServiceBroadcastReceiver.CHECK_SERVICE_ACTION);
            itemsIntent.putExtra(CheckServiceBroadcastReceiver.Status.NewItem.name(), (TreeSet) allItems);
            sendBroadcast(itemsIntent);
        }
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, CheckActivity.class);
        notificationPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent stopIntent = new Intent(this, CheckService.class);
        stopIntent.putExtra(CheckServiceBroadcastReceiver.Status.StopService.name(), true);
        stopPendingIntent = PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.check_service))
                .setContentText(getString(R.string.nothing_found))
                .setSmallIcon(R.drawable.triangle)
                .setContentIntent(notificationPendingIntent)
                .addAction(R.drawable.square, getString(R.string.stop), stopPendingIntent)
                .build();
    }

    private void launchService(Notification notification) {
        startForeground(1, notification);
        presenter.onServiceReady();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void sendNewItems(Set<Item> items) {
        Intent intent = new Intent();
        intent.setAction(CheckServiceBroadcastReceiver.CHECK_SERVICE_ACTION);
        intent.putExtra(CheckServiceBroadcastReceiver.Status.NewItem.name(), (TreeSet) items);
        sendBroadcast(intent);
    }

    @Override
    public void notifyNewItem() {
        newItemsCount++;
        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.check_service))
                .setContentText(getString(R.string.new_items_found) + newItemsCount)
                .setSmallIcon(R.drawable.triangle)
                .setContentIntent(notificationPendingIntent)
                .addAction(R.drawable.square, getString(R.string.stop), stopPendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();
        notificationManager.notify(1, notification);
    }

    @NonNull
    @Override
    public MvpPresenter<CheckServiceMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public class CheckBinder extends Binder implements Listener {

        @Override
        public Set<Item> getFoundItems() {
            return presenter.onItemsRequest();
        }

        @Override
        public void onParametersChanged() {
            presenter.onParametersChanged();
        }

        @Override
        public void stopService() {
            stopSelf();
        }
    }

    public interface Listener {

        Set<Item> getFoundItems();

        void onParametersChanged();

        void stopService();
    }
}