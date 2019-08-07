package com.fgtmaps.android.sdk.samples.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fgtmaps.android.sdk.samples.R;
import com.fgtmaps.android.sdk.samples.utils.MapUtils;
import com.tplmaps3d.Circle;
import com.tplmaps3d.CircleOptions;
import com.tplmaps3d.IconFactory;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.MapView;
import com.tplmaps3d.Marker;
import com.tplmaps3d.MarkerOptions;
import com.tplmaps3d.Polygon;
import com.tplmaps3d.PolygonOptions;
import com.tplmaps3d.Polyline;
import com.tplmaps3d.PolylineOptions;
import com.tplmaps3d.sdk.model.PointOfInterest;

import java.util.ArrayList;


public class ActivityShapes extends AppCompatActivity implements MapView.OnMapReadyCallback {

    private static final String TAG = ActivityMaps.class.getSimpleName();

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        mMapView = findViewById(R.id.map);

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

        mapController.setLngLat(new LngLat(39.825521, 21.421365));
        mapController.setZoomBy(15);
        addMarkers(mapController);
        addPolyLines(mapController);
        addPolygons(mapController);
        addCircles(mapController);

        mapController.setOnMapClickListener(new MapController.OnMapClickListener() {
            @Override
            public void onMapClick(LngLat lngLat) {
                Log.i(TAG, "Called: onMapClick lnglat = " + lngLat.longitude + " , " + lngLat.latitude);
                //marker1.setPositionEased(lngLat, 1);
                //mMapController.addMarker(new MarkerOptions().position(lngLat));
            }
        });

        mapController.setOnPoiClickListener(new MapController.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest place) {
                Log.i(TAG, "Called: onPoiClick id = " + place.id);
                Log.i(TAG, "Called: onPoiClick tile = " + place.nameEn);
                Log.i(TAG, "Called: onPoiClick lnglat = " + place.lngLat.longitude + " , " + place.lngLat.latitude);
                Log.i(TAG, " // /// ///// /// ");
            }
        });

        mapController.setOnMarkerClickListener(new MapController.OnMarkerClickListener() {
            @Override
            public void onMarkerClick(Marker tplMarker) {
                Log.i(TAG, "Called: tplMarker tile = " + tplMarker.getTitle());
            }
        });

        mapController.setOnInfoWindowClickListener(new MapController.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker tplMarker) {
                Log.i(TAG, "Called: tplMarker snippet = " + tplMarker.getDescription());
            }
        });

        mapController.setOnPolylineClickListener(new MapController.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline tplPolyline) {
                Log.i(TAG, "Called: tplPolyline order = " + tplPolyline.getOrder());
            }
        });

        mapController.setOnPolygonClickListener(new MapController.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon tplPolygon) {
                Log.i(TAG, "Called: tplPolygon stroke width = " + tplPolygon.getOutlineWidth());
                //mapController.clearMap();
            }
        });

        mapController.setOnCircleClickListener(new MapController.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle tplCircle) {
                Log.i(TAG, "Called: tplCircle stroke radius = " + tplCircle.getRadius());
                Log.i(TAG, "Called: total markers = " + mapController.getAllMarkers().size());
                Log.i(TAG, "Called: total polylines = " + mapController.getAllPolyLines().size());
                Log.i(TAG, "Called: total polygons = " + mapController.getAllPolygons().size());
                Log.i(TAG, "Called: total circles = " + mapController.getAllCircles().size());
            }
        });

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

    Marker marker1;

    private void addMarkers(final MapController mapController) {

        LngLat latLngISB = new LngLat(39.824676, 21.422068);
        marker1 = mapController.addMarker(new MarkerOptions()
                .position(latLngISB)
                .title("marker1")
                .description("This is my spot!").infoWindowOffset(new android.graphics.Point(-150, 0))
                .rotation(50).flat(true)
                .icon(IconFactory.fromResource(R.drawable.ic_pin_drop)).visible(false).order(1));

        marker1.setFlat(false);
        marker1.setRotation(0);
        marker1.setInfoWindowOffset(new android.graphics.Point(0, 0));
        marker1.showInfoWindow();
        marker1.setVisible(true);
        marker1.showInfoWindow();

        marker1.setProperties(latLngISB);

        marker1.setTitle("Sample Title 1");
        marker1.setDescription("Sample Desc1");

        final Marker marker2 = mapController.addMarker(new MarkerOptions()
                .position(new LngLat(39.826714, 21.420254))
                .title("Sample Title2")
                .description("Sample Desc2").flat(false).order(0));

        marker2.showInfoWindow();
        marker2.setTitle("Sample Title 2");

        mapController.removeMarker(marker2);

    }

    private void addPolyLines(MapController mapController) {

        final Polyline polyline1 = mapController.addPolyline(new PolylineOptions()
                .add(new LngLat(39.823554, 21.421001),
                        new LngLat(39.824137, 21.420683),
                        new LngLat(39.824417, 21.420563),
                        new LngLat(39.824829, 21.420462),
                        new LngLat(39.826862, 21.420122))
                .color(Color.WHITE).width(10).order(5).outlineWidth(2)
                .outlineColor(Color.BLUE).clickable(true));
        polyline1.setOutlineWidth(5);
        polyline1.setOutlineColor(Color.parseColor("#FF69B4"));

        final Polyline polyline2 = mapController.addPolyline(new PolylineOptions()
                .add(new LngLat(71.094177, 33.729113),
                        new LngLat(73.090913, 33.727616)).color(Color.RED).width(5).order(5));
        polyline2.remove();
    }

    private void addPolygons(MapController mapController) {

        ArrayList<LngLat> lngLats1 = new ArrayList<>();
        lngLats1.add(new LngLat(39.826129, 21.422520));
        lngLats1.add(new LngLat(39.826202, 21.422565));
        lngLats1.add(new LngLat(39.826257, 21.422482));
        lngLats1.add(new LngLat(39.826187, 21.422437));
        lngLats1.add(new LngLat(39.826129, 21.422520));
        Polygon tplPolygon = mapController.addPolygon(new PolygonOptions().addAll(lngLats1).order(2)
                .fillColor(Color.BLACK).outlineColor(Color.GRAY).outlineWidth(7));
        tplPolygon.setClickable(true);

        ArrayList<LngLat> lngLats2 = new ArrayList<>();
        lngLats2.add(new LngLat(39.826129, 21.422520));
        lngLats2.add(new LngLat(39.826202, 21.422565));
        lngLats2.add(new LngLat(39.826257, 21.422482));
        lngLats2.add(new LngLat(39.826187, 21.422437));
        lngLats2.add(new LngLat(39.826129, 21.422520));
        Polygon tplPolygon1 = mapController.addPolygon(new PolygonOptions().addAll(lngLats2)
                .order(2).fillColor(Color.GRAY).outlineColor(Color.BLACK).outlineWidth(4).clickable(true));

        mapController.removePolygon(tplPolygon);
    }

    private void addCircles(MapController mapController) {

        Circle tplCircle = mapController.addCircle(new CircleOptions()
                .center(new LngLat(39.826201, 21.422476))
                .radius(30).fillColor(Color.CYAN)
                .order(1).clickable(true));
        /*tplCircle.setStrokeColor(Color.CYAN);
        tplCircle.setFillColor(Color.YELLOW);
        tplCircle.setOrder(2);*/

        //tplCircle.setFillColor(Color.parseColor("#50FF00FF"));
        //tplCircle.setStrokeColor(Color.TRANSPARENT);

        Circle tplCircle1 = mapController.addCircle(new CircleOptions()
                .center(new LngLat(39.824676, 21.422068))
                .radius(150).fillColor(Color.BLUE)
                .order(1).clickable(true));

        tplCircle.remove();
        //tplCircle1.remove();
        //mapController.removeCircle(tplCircle);
        //mapController.removeAllCircles();
    }
}
