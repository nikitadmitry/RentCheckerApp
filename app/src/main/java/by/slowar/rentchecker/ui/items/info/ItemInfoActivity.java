package by.slowar.rentchecker.ui.items.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.mvp.MvpActivity;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.di.modules.RouterModule;
import by.slowar.rentchecker.ui.items.info.adapter.PhotoItemInfoAdapter;
import by.slowar.rentchecker.ui.items.info.photo.PhotoDialog;

public class ItemInfoActivity extends MvpActivity<ItemInfoMvp.View> implements ItemInfoMvp.View,
        OnMapReadyCallback, PhotoItemInfoAdapter.Listener {

    private static final String ITEM_INFO = "itemInfo";
    private final String PHOTO_DIALOG = "PhotoDialog";

    private ItemInfoActivityBinding binding;

    @Inject
    public ItemInfoMvp.Presenter presenter;

    private GoogleMap googleMap;
    private MapView mapView;
    private Item item;

    public static Intent newIntent(Context context, Item item) {
        Intent intent = new Intent(context, ItemInfoActivity.class);
        intent.putExtra(ITEM_INFO, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_info);
        setSupportActionBar((Toolbar) binding.toolbar);
        App.getAppComponent().getScreenComponent(new RouterModule(this)).inject(this);

        setItemFromIntent();
        setupGoogleMap(savedInstanceState);
    }

    private void setItemFromIntent() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            item = (Item) arguments.getSerializable(ITEM_INFO);
            binding.setItem(item);
            binding.setView(this);
            binding.photosRecycler.setAdapter(new PhotoItemInfoAdapter(item.getPhotoUrlList(), this));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void setupGoogleMap(Bundle savedInstanceState) {
        mapView = binding.itemMap;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onCallButtonClick(String number) {
        presenter.callButtonClicked(number);
    }

    @NonNull
    @Override
    public MvpPresenter<ItemInfoMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMinZoomPreference(12);
        LatLng itemLocation = new LatLng(item.getLocationLatitude(), item.getLocationLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(itemLocation));
        googleMap.addMarker(new MarkerOptions().position(itemLocation).title(item.getAddress()));
    }

    @Override
    public void onImageClick(ImageView imageView) {
        PhotoDialog photoDialog = PhotoDialog.newInstance();
        photoDialog.show(getSupportFragmentManager(), PHOTO_DIALOG);
        photoDialog.setImage(imageView.getDrawable());
    }
}