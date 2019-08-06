package com.fgtmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fgtmaps.android.sdk.samples.R;
import com.fgtmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.utils.CommonUtils;

public class ActivityMaps extends AppCompatActivity implements MapView.OnMapReadyCallback {

    //private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initializing and getting MapView resource
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

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

        CommonUtils.showToast(this, "Map Ready", Toast.LENGTH_SHORT, false);

        // DEFAULT SETTINGS
        // Zoom to default location
        mapController.setLngLatZoom(new LngLat(44.428663, 25.007969), 5f);
        // Setting map max tilt value
        mapController.setMaxTilt(85);

        // TODO: Map loaded and ready, write your map tasks here
    }
}
