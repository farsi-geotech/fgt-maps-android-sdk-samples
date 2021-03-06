package com.fgtmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.fgtmaps.android.sdk.samples.R;
import com.fgtmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapMode;
import com.tplmaps3d.MapView;

public class ActivityMapFeatures extends AppCompatActivity implements MapView.OnMapReadyCallback,
        CompoundButton.OnCheckedChangeListener {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    final boolean ENABLE_NIGHT_MODE_DEFAULT = false;
    //final boolean ENABLE_BUILDINGS_DEFAULT = true;
    final boolean ENABLE_POIS_DEFAULT = true;
    final boolean ENABLE_TRAFFIC_DEFAULT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_features);

        // Initializing and getting MapView resource
        mMapView = findViewById(R.id.map);

        CheckBox cbNightMode = findViewById(R.id.cb_night_mode);
        cbNightMode.setOnCheckedChangeListener(this);
        cbNightMode.setChecked(ENABLE_NIGHT_MODE_DEFAULT);
        // Setting Night Mode as default
        onCheckedChanged(cbNightMode, ENABLE_NIGHT_MODE_DEFAULT);

        /*CheckBox cbBuildings = findViewById(R.id.cb_buildings);
        cbBuildings.setOnCheckedChangeListener(this);
        cbBuildings.setChecked(ENABLE_BUILDINGS_DEFAULT);
        // Setting Buildings as default
        onCheckedChanged(cbBuildings, ENABLE_BUILDINGS_DEFAULT);*/

        CheckBox cbPOIs = findViewById(R.id.cb_pois);
        cbPOIs.setOnCheckedChangeListener(this);
        cbPOIs.setChecked(ENABLE_POIS_DEFAULT);
        // Setting POIs as default
        onCheckedChanged(cbPOIs, ENABLE_POIS_DEFAULT);

        CheckBox cbTraffic = findViewById(R.id.cb_traffic);
        cbTraffic.setOnCheckedChangeListener(this);
        cbTraffic.setChecked(ENABLE_TRAFFIC_DEFAULT);
        // Setting POIs as default
        onCheckedChanged(cbTraffic, ENABLE_TRAFFIC_DEFAULT);

        // OR you can make settings for map defaults by calling these methods before call to load maps
        /*mMapView.setMapMode(MapMode.DEFAULT);
        mMapView.setPOIsEnabled(true);
        mMapView.setTrafficEnabled(true);*/

        // Loading map Asynchronously
        MapUtils.initAndLoadMaps(savedInstanceState, mMapView, this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mMapView != null)
            mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mMapView != null)
            mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMapView != null)
            mMapView.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

        if (mMapView != null)
            mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(final MapController mapController) {
        // TODO: Map loaded and ready, write your map tasks here

        // Loading Default Map Controls
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);

        // Zoom to default location
        mapController.setLngLatZoom(new LngLat(39.168926, 21.506630), 15f);
        // Setting map max tilt value
        mapController.setMaxTilt(85);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        switch (compoundButton.getId()) {

            case R.id.cb_night_mode:
                mMapView.setMapMode((checked) ? MapMode.NIGHT : MapMode.DEFAULT);
                break;

            /*case R.id.cb_buildings:
                mMapView.setBuildingsEnabled(checked);
                break;*/

            case R.id.cb_pois:
                mMapView.setPOIsEnabled(checked);
                break;

            case R.id.cb_traffic:
                mMapView.setTrafficEnabled(checked);
                break;
        }
    }
}
