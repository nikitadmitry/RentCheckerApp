package by.slowar.rentchecker.ui.map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import by.slowar.mvp.presenter.MvpPresenter;
import by.slowar.rentchecker.App;
import by.slowar.rentchecker.R;
import by.slowar.rentchecker.common.mvp.MvpFragment;
import by.slowar.rentchecker.data.model.Item;
import by.slowar.rentchecker.di.modules.RouterModule;

public class MapFragment extends MvpFragment<MapMvp.View> implements MapMvp.View, OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    @Inject
    public MapMvp.Presenter presenter;

    private MapFragmentBinding binding;
    private Listener mListener;
    private MapView mapView;
    private GoogleMap googleMap;
    private Set<Item> addedMarkerItems;
    private boolean isMapReady;
    private Marker selectedMarker;

    public MapFragment() {
        // Required empty public constructor
        addedMarkerItems = new TreeSet<>();
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        App.getAppComponent().getScreenComponent(new RouterModule(getContext())).inject(this);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Listener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        setupGoogleMap(savedInstanceState);
        return binding.getRoot();
    }

    private void setupGoogleMap(Bundle savedInstanceState) {
        mapView = binding.allItemsMap;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMarkerClickListener(this);
        googleMap.setMinZoomPreference(10);
        LatLng ny = new LatLng(53.9f, 27.56667f);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        isMapReady = true;
    }

    public void addItemsToMap(Set<Item> items) {
        if (!isMapReady) {
            return;
        }

        for (Item item : items) {
            boolean isAdded = addedMarkerItems.add(item);
            if (isAdded) {
                LatLng itemLocation = new LatLng(item.getLocationLatitude(), item.getLocationLongitude());
                String title = item.getAddress() + " " + (int) item.getPrice() + "$";
                Marker marker = googleMap.addMarker(new MarkerOptions().position(itemLocation).title(title));
                marker.setTag(item);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public MvpPresenter<MapMvp.View> getPresenter() {
        return presenter;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (selectedMarker != null && selectedMarker.equals(marker)) {
            selectedMarker.hideInfoWindow();
            Item item = (Item) marker.getTag();
            presenter.itemClicked(item);
            selectedMarker = null;
        } else {
            selectedMarker = marker;
            selectedMarker.showInfoWindow();
        }
        return false;
    }

    public interface Listener {
    }
}