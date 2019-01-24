package com.arintv.conlabs.witopentrack.examples;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.arintv.conlabs.witopentrack.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Activity_Example_LocationManager extends AppCompatActivity implements MapView.MapViewEventListener {

    private double userLatitude;
    private double userLongitude;

    LocationManager locationManager;
    MapView mapView;
    ToggleButton LocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example_locationmanager);

        LocationButton = findViewById(R.id.Location_Toggle);
        TextView LocationDataLATI = findViewById(R.id.Location_CurrentDataLATI);
        TextView LocationDataLONGI = findViewById(R.id.Location_CurrentDataLONGI);

        mapView = findViewById(R.id.Location_MapView);
        mapView.setMapViewEventListener(this);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Current User Position");
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        LocationButton.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked) {
                CurrentLocationWGS84();
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(userLatitude, userLongitude), true);
                LocationDataLATI.setText("위도: " + userLatitude);
                LocationDataLONGI.setText("경도: " + userLongitude);

                double LatiNum = userLatitude;
                double LongiNum = userLongitude;

                if(LatiNum != 0.0 && LongiNum != 0.0) {
                    marker.setMapPoint(MapPoint.mapPointWithGeoCoord(LatiNum, LongiNum));
                    mapView.addPOIItem(marker);
                }
            } else {
                locationManager.removeUpdates(locationlistener);
                mapView.removeAllPOIItems();
            }
        });
    }

    @Override
    public void onBackPressed() {
        locationManager.removeUpdates(locationlistener);
        mapView.removeAllPOIItems();
        finish();
    }

    private void CurrentLocationWGS84() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        final int LocationUpdateTime = 1 * 1000; // 대부분의 딜레이 타임은 밀리세컨드(ms);
        final float LocationUpdateDistance = 10.0f; // 미터 단위

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LocationUpdateTime, LocationUpdateDistance, locationlistener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LocationUpdateTime, LocationUpdateDistance, locationlistener);;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private final LocationListener locationlistener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            userLatitude = location.getLatitude();
            userLongitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.555145,126.970788), true);
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        LocationButton.setChecked(false);
        locationManager.removeUpdates(locationlistener);
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}
