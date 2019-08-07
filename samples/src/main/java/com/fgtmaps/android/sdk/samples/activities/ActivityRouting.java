package com.fgtmaps.android.sdk.samples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fgt.maps.sdk.routing.IMapRoute;
import com.fgt.maps.sdk.routing.RouteConfig;
import com.fgt.maps.sdk.routing.RouteManager;
import com.fgt.maps.sdk.routing.structures.Place;
import com.fgt.maps.sdk.routing.structures.Route;
import com.fgt.maps.sdk.routing.structures.TPLRouteDirection;
import com.fgtmaps.android.sdk.samples.R;

import java.util.ArrayList;

public class ActivityRouting extends AppCompatActivity {

    RouteManager mRouteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        mRouteManager = new RouteManager();
    }

    public void calculateRoute(View v) {
        v.setVisibility(View.GONE);

        // Initializing/preparing source and destination locations array
        ArrayList<Place> locations = new ArrayList<>();
        // Source location
        Place source = new Place();
        source.setName("King Abdullah Bin Abdulaziz Square, Jeddah");
        source.setX(39.170069);
        source.setY(21.507161);
        // Destination Location
        Place destination = new Place();
        destination.setName("Kudy, Makkah, Saudi Arabia");
        destination.setX(39.839409);
        destination.setY(21.390267);

        locations.add(source);
        locations.add(destination);

        // Building route config
        RouteConfig config = new RouteConfig.Builder()
                .reRoute(false)
                .endPoints(locations)
                .heading(90)
                .build();

        // Calling for calculating routes for source and destination locations with config
        mRouteManager.calculate(this, config, new IMapRoute() {
            @Override
            public void onMapRoutingOverview(ArrayList<Place> endPoints, ArrayList<Route> routes) {
                StringBuilder response;
                response = new StringBuilder("Start: " + endPoints.get(0).getName()
                        + " (" + endPoints.get(0).getY() +
                        "," + endPoints.get(0).getX() + ")\n");
                response.append("End: ").append(endPoints.get(1).getName()).append(" (")
                        .append(endPoints.get(1).getY()).append(",")
                        .append(endPoints.get(1).getX()).append(")\n");

                int routeNo = 0;
                for (Route route : routes) {
                    response.append("\n\nRoute ").append(++routeNo)
                            .append("\nRoute Length (In Meters): ").append(route.getTotalLength())
                            .append("\n").append("Route Time (In Milliseconds): ")
                            .append(route.getTotalTime()).append("\n");
                    response.append("Turns: \n");
                    for (TPLRouteDirection routeDirection : route.getListRouteDirections()) {
                        response.append(routeDirection.getCompleteText()).append("\n");
                    }
                    response.append("-----------------------------------");
                }

                ((TextView) findViewById(R.id.tvResponse)).setText(response.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRouteManager.onDestroy();
        mRouteManager = null;
    }
}
