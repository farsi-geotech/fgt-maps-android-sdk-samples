package com.fgtmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fgtmaps.android.sdk.samples.R;
import com.fgtmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.CameraPosition;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.sdk.model.Bounds;


public class ActivityCamera extends AppCompatActivity implements MapView.OnMapReadyCallback,
        MapController.OnCameraChangeStartedListener, MapController.OnCameraChangeListener,
        MapController.OnCameraChangeEndListener {

    private MapView mMapView;
    private static final String TAG = ActivityCamera.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Initializing and getting MapView resource
        mMapView = findViewById(R.id.map);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMapView.getMapController() == null)
                    return;

                mMapView.getMapController().setCamera(CameraPosition.builder(mMapView.getMapController())
                        .position(new LngLat(39.763722, 21.344798))
                        .tilt(0)
                        .build());

                // Zoom camera to extent(bounding box) of Mecca City(Saudi Arabia) with animation
                // BoundingBox needs two location points south-west and north-east respectively
                // to zoom on an extent
                mMapView.getMapController().setBounds(
                        new Bounds(new LngLat(39.763722, 21.344798),
                                new LngLat(39.907718, 21.473638)), 200, 2000);

                // Zoom to area bound in a rect specified on screen
                /*mMapView.getMapController().setBoundsInWindow(
                        new Bounds(new LngLat(73.035070, 33.637313), new LngLat(73.041247, 33.659408)),
                        new Rect(50, 50, 720, 1118),
                        150, 2000);*/
            }
        });

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
    public void onMapReady(MapController mapController) {
        // TODO: Do your map tasks here

        mapController.setOnCameraChangeStartedListener(this);
        mapController.setOnCameraChangeListener(this);
        mapController.setOnCameraChangeEndListener(this);

        // Tilted Zoom on Kaaba's location
        mapController.animateCamera(CameraPosition.builder(mapController)
                .position(new LngLat(39.826185, 21.422509))
                .zoom(15f)
                .tilt(0.5f)
                .build(), 2000);


        // DEFAULT/SAMPLE SETTINGS
        // Setting map max tilt value
        mapController.setMaxTilt(85);
        // Loading Default Map Controls
        mapController.getLocationConfig()
                .setLocationSettings(true)
                .setPermissionRequestIfDenied(true)
                .setPermissionReasonDialogContent("Permission Required",
                        "Location permission is required for the application to show your" +
                                " precise and accurate location on map");
        mapController.getUiSettings().showZoomControls(true);
        mapController.getUiSettings().showMyLocationButton(true);
    }

    @Override
    public void onCameraChangeStarted(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Change Started");
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Changing");
    }

    @Override
    public void onCameraChangeEnd(CameraPosition cameraPosition) {
        Log.i(TAG, "Camera Change End");
    }
}
