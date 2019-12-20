package by.slowar.rentchecker.ui.check;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Set;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.mvp.MvpActivity;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.di.modules.RouterModule;
import by.slowar.rentchecker.ui.check.adapter.CheckFragmentPagerAdapter;
import by.slowar.rentchecker.ui.check.service.CheckService;
import by.slowar.rentchecker.ui.check.service.CheckServiceBroadcastReceiver;
import by.slowar.rentchecker.ui.items.ItemsFragment;
import by.slowar.rentchecker.ui.map.MapFragment;

public class CheckActivity extends MvpActivity<CheckMvp.View> implements CheckMvp.View,
        ItemsFragment.Listener, MapFragment.Listener, CheckServiceBroadcastReceiver.CheckServiceListener,
        ServiceConnection {

    public static final int ITEMS_FRAGMENT = 0;
    public static final int MAP_FRAGMENT = 1;

    private ActivityCheckBinding binding;
    private int startStopIconResourceId;
    private CheckServiceBroadcastReceiver checkServiceBroadcastReceiver;
    private CheckService.Listener checkServiceListener;
    private Intent checkServiceIntent;
    private boolean isServiceBound;
    private boolean isParametersUpdated;

    @Inject
    public CheckMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check);
        setSupportActionBar((Toolbar) binding.toolbar);

        CheckFragmentPagerAdapter pagerAdapter = new CheckFragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, CheckActivity.this);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(viewPager);

        App.getAppComponent().getScreenComponent(new RouterModule(this)).inject(this);

        startStopIconResourceId = R.drawable.triangle;
        checkServiceIntent = new Intent(this, CheckService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(checkServiceIntent, this, 0);
        setupCheckServiceBroadcast();
    }

    private void setupCheckServiceBroadcast() {
        checkServiceBroadcastReceiver = new CheckServiceBroadcastReceiver(this);
        IntentFilter checkBroadcastIntentFilter = new IntentFilter();
        checkBroadcastIntentFilter.addAction(CheckServiceBroadcastReceiver.CHECK_SERVICE_ACTION);
        registerReceiver(checkServiceBroadcastReceiver, checkBroadcastIntentFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.start_stop_item);
        menuItem.setIcon(ContextCompat.getDrawable(this, startStopIconResourceId));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_stop_item:
                if (isServiceBound) {
                    checkServiceListener.stopService();
                } else {
                    ContextCompat.startForegroundService(this, checkServiceIntent);
                    bindService(checkServiceIntent, this, 0);
                }
                break;
            case R.id.settings_item:
                if (!isServiceBound) {
                    presenter.parametersButtonClicked();
                    isParametersUpdated = true;
                } else {
                    Toast.makeText(this, getString(R.string.stop_service_warning), Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStopService() {
        switchStartStopButton();
    }

    private void switchStartStopButton() {
        if (!isServiceBound) {
            startStopIconResourceId = R.drawable.triangle;
        } else {
            startStopIconResourceId = R.drawable.square;
        }
        invalidateOptionsMenu();
    }


    @Override
    public void addNewItems(Set<Item> items) {
        CheckFragmentPagerAdapter adapter = (CheckFragmentPagerAdapter) binding.viewPager.getAdapter();
        if (adapter != null) {
            ItemsFragment itemsFragment = (ItemsFragment) adapter.getRegisteredFragment(CheckActivity.ITEMS_FRAGMENT);
            if (itemsFragment != null) {
                itemsFragment.addItemsToList(items);
            }
            MapFragment mapFragment = (MapFragment) adapter.getRegisteredFragment(CheckActivity.MAP_FRAGMENT);
            if (mapFragment != null) {
                mapFragment.addItemsToMap(items);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkServiceBroadcastReceiver.unregister();
        unregisterReceiver(checkServiceBroadcastReceiver);
        if (isServiceBound) {
            unbindService(this);
            isServiceBound = false;
        }
    }

    @NonNull
    @Override
    public MvpPresenter<CheckMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        checkServiceListener = (CheckService.Listener) service;
        isServiceBound = true;
        updateServiceOnParameters();
        tryToRefreshItemsFromService();
        switchStartStopButton();
    }

    private void updateServiceOnParameters() {
        if (isParametersUpdated) {
            checkServiceListener.onParametersChanged();
            isParametersUpdated = false;
        }
    }

    private void tryToRefreshItemsFromService() {
        Set<Item> foundItems = checkServiceListener.getFoundItems();
        addNewItems(foundItems);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isServiceBound = false;
        switchStartStopButton();
    }
}