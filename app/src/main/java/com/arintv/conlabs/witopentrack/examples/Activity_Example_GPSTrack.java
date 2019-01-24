package com.arintv.conlabs.witopentrack.examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.arintv.conlabs.witopentrack.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Activity_Example_GPSTrack extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, MapView.MapViewEventListener {

    ImageButton GPSTrackMenu;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example_gpstrack);

        mapView = findViewById(R.id.GPSTrackMapView);
        mapView.setMapViewEventListener(this);
        GPSTrackMenu = findViewById(R.id.GPSTrackMenu);
    }

    // XML ImageView의 OnClick 속성 적용
    protected void CallMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_example_gpstrack, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.GPSTrack_Off:
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                break;
            case R.id.GPSTrack_OnWithoutHead:
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
                break;
            case R.id.GPSTrack_OnHead:
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
                break;
            default:
        }
        return false;
    }

    // MapView 초기화
    @Override
    public void onMapViewInitialized(MapView mapView) {
        // 좌표 위치는 서울역: Google Map의 WGS84 제공좌표 기준
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.555145,126.970788), true);
    }

    // 지도 중앙 좌표가 바뀌었을때: 1회라도 움직였을 때
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        // 트랙킹 모드를 종료합니다.
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    }


    // MapViewEventListener에서 사용하지 않는 부분
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
