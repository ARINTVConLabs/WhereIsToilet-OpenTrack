package com.arintv.conlabs.witopentrack.examples;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.arintv.conlabs.witopentrack.App_Extra_Resources;
import com.arintv.conlabs.witopentrack.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Activity_Example_URLScheme extends AppCompatActivity implements MapView.MapViewEventListener, PopupMenu.OnMenuItemClickListener {

    MapView mapView;
    EditText Desti_Latitude, Desti_Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example_urlscheme);

        mapView = findViewById(R.id.URLScheme_MapView);

        mapView.setMapViewEventListener(this);

        Desti_Latitude = findViewById(R.id.URLScheme_Dest_Latitude);
        Desti_Longitude = findViewById(R.id.URLScheme_Dest_Longitude);
    }

    protected void CallSchemeButton(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_example_urlscheme, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(this);

    }

    private void SchemeStart(String URLScheme) {
        App_Extra_Resources extraResources = new App_Extra_Resources(this);
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URLScheme));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            extraResources.callGooglePlayAppLink("market://details?id=net.daum.android.map", R.string.example_scheme_NotFound);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String DLatiValue = Desti_Latitude.getText().toString();
        String DLongiValue = Desti_Longitude.getText().toString();

        switch(item.getItemId()) {
            // 카카오맵 오픈
            case R.id.URLScheme_OpenMaps:
                SchemeStart("daummaps://open");
                break;
            case R.id.URlScheme_PlaceSearch:
                SchemeStart("daummaps://open?page=placeSearch");
                break;
            case R.id.URLScheme_RouteSearch:
                SchemeStart("daummaps://open?page=routeSearch");
                break;
            case R.id.URLScheme_WGSMove:
                SchemeStart("daummaps://look?p=" + DLatiValue + "," + DLongiValue);
                break;
        }
        return false;
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.555145,126.970788), 2, true);
        Desti_Latitude.setText(Double.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude).toString());
        Desti_Longitude.setText(Double.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().longitude).toString());
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Desti_Latitude.setText(String.valueOf(mapPoint.getMapPointGeoCoord().latitude));
        Desti_Longitude.setText(String.valueOf(mapPoint.getMapPointGeoCoord().longitude));
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
