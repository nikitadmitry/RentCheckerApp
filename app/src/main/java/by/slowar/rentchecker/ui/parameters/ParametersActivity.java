package by.slowar.rentchecker.ui.parameters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.Map;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.Constants;
import by.slowar.rentchecker.common.mvp.MvpActivity;
import by.slowar.rentchecker.data.model.ItemLocation;
import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.data.model.ParametersObject;
import by.slowar.rentchecker.di.modules.RouterModule;

public class ParametersActivity extends MvpActivity<ParametersMvp.View> implements ParametersMvp.View,
        OnRangeSeekbarChangeListener, OnRangeSeekbarFinalValueListener {

    @Inject
    public ParametersMvp.Presenter presenter;
    private ParametersActivityBinding binding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ParametersActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parameters);
        App.getAppComponent().getScreenComponent(new RouterModule(this)).inject(this);
        binding.setPresenter(presenter);
        setSupportActionBar((Toolbar) binding.parametersToolbar);

        setupRangeSeekBar();
    }

    private void setupRangeSeekBar() {
        CrystalRangeSeekbar rangeSeekbar = binding.priceRangeBar;
        rangeSeekbar.setOnRangeSeekbarChangeListener(this);
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(this);
        rangeSeekbar.setMinValue(Constants.MIN_PRICE);
        rangeSeekbar.setMaxValue(Constants.MAX_PRICE);
        rangeSeekbar.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.viewIsReady();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parameters_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.apply_item:
                presenter.applyButtonClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public MvpPresenter<ParametersMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        applyParameters();
    }

    private void applyParameters() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
    }

    @Override
    public void setParametersValues(ParametersObject parameters) {
        binding.priceRangeBar.setMinStartValue(parameters.getMinPrice());
        binding.priceRangeBar.setMaxStartValue(parameters.getMaxPrice());
        binding.priceRangeBar.apply();
        valueChanged(parameters.getMinPrice(), parameters.getMaxPrice());

        setRoomsButtons(parameters);

        binding.ownerSwitch.setChecked(parameters.isOwner());
        binding.citiesGroup.check(getCityRadioButtonId(parameters.getCity()));

        setSitesCheckboxes(parameters);

        binding.executePendingBindings();
    }

    private int getCityRadioButtonId(ItemLocation.City city) {
        switch (city) {
            case Minsk:
            default:
                return R.id.minsk_radio;
        }
    }

    private void setRoomsButtons(ParametersObject parameters) {
        Map<ParametersPreferences.RoomType, Boolean> roomsMap = parameters.getRoomsMap();
        ConstraintLayout roomsLayout = binding.roomsButtonsLayout;
        for (int i = 0; i < roomsLayout.getChildCount(); i++) {
            ToggleButton toggleButton = (ToggleButton) roomsLayout.getChildAt(i);
            ParametersPreferences.RoomType roomType = ParametersPreferences.RoomType.values()[i];
            if (roomsMap.containsKey(roomType)) {
                Boolean isChecked = roomsMap.get(roomType);
                if (isChecked == null) {
                    throw new IllegalStateException("Boolean value of sites checkbox cannot be null!");
                }
                toggleButton.setChecked(isChecked);
            }
        }
    }

    private void setSitesCheckboxes(ParametersObject parameters) {
        ConstraintLayout sitesLayout = binding.sitesCheckboxesLayout;
        Map<RentItemsHelper.Site, Boolean> sitesMap = parameters.getSitesMap();
        for (int i = 0; i < sitesLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) sitesLayout.getChildAt(i);
            RentItemsHelper.Site site = RentItemsHelper.Site.values()[i];
            if (sitesMap.containsKey(site)) {
                Boolean isChecked = sitesMap.get(site);
                if (isChecked == null) {
                    throw new IllegalStateException("Boolean value of sites checkbox cannot be null!");
                }
                checkBox.setChecked(isChecked);
            }
        }
    }

    @Override
    public void valueChanged(Number minValue, Number maxValue) {
        String text = minValue + " - " + maxValue;
        binding.valueRangeText.setText(text);
    }

    @Override
    public void finalValue(Number minValue, Number maxValue) {
        presenter.minPriceChanged(minValue.intValue());
        presenter.maxPriceChanged(maxValue.intValue());
    }
}