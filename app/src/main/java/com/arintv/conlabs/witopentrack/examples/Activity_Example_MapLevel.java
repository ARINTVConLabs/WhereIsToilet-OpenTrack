package com.arintv.conlabs.witopentrack.examples;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arintv.conlabs.witopentrack.App_Extra_Resources;
import com.arintv.conlabs.witopentrack.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Activity_Example_MapLevel extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, MapView.MapViewEventListener {

    MapView mapView;

    int CurrentProgress;

    App_Extra_Resources ExtraResources = new App_Extra_Resources(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example_maplevel);

        mapView = findViewById(R.id.LevelMapView);
        mapView.setMapViewEventListener(this);

        // Zoomlevel이 줄어들면 화면에 보이는 영역이 감소합니다.
        Button ZoomlevelDown = findViewById(R.id.LevelMap_Zoomleveldown);
        Button ZoomlevelUp = findViewById(R.id.LevelMap_ZoomlevelUp);

        ZoomlevelDown.setOnClickListener(v -> mapView.zoomIn(true));
        ZoomlevelUp.setOnClickListener(v -> mapView.zoomOut(true));
    }

    protected void LevelCallMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_example_maplevel, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MapLevel_Zoomlevel:
                ZoomlevelDialog();
                break;
            case R.id.MapLevel_WGSLocation:
                WGSDialog();
                break;
            case R.id.MapLevel_ZoomlevelAndWGS:
                ZoomlevelAndWGS();
                break;
            default:
        }
        return false;
    }

    private void ZoomlevelDialog() {
        Dialog CustomDialog_Zoomlevel = new Dialog(this);
        CustomDialog_Zoomlevel.setContentView(R.layout.dialog_maplevel_zoomlevel);

        ExtraResources.setCustomDialogSize(CustomDialog_Zoomlevel, 0.8f, 0.3f);

        SeekBar seekBar = CustomDialog_Zoomlevel.findViewById(R.id.MapLevelDialog_LevelSeekBar);
        Button Accept = CustomDialog_Zoomlevel.findViewById(R.id.MapLevelDialog_Accept);
        Button Cancel = CustomDialog_Zoomlevel.findViewById(R.id.MapLevelDialog_Cancel);
        TextView LevelSeekBarValue = CustomDialog_Zoomlevel.findViewById(R.id.MapLevelDialog_LevelValue);

        LevelSeekBarValue.setText(String.valueOf(CurrentProgress+1));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CurrentProgress = progress;
                LevelSeekBarValue.setText(String.valueOf(CurrentProgress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Accept.setOnClickListener(v -> {
            mapView.setZoomLevel(CurrentProgress+1,true);
            CustomDialog_Zoomlevel.dismiss();
        });
        Cancel.setOnClickListener(v -> {
            CustomDialog_Zoomlevel.dismiss();
        });

        CustomDialog_Zoomlevel.show();
    }

    private void WGSDialog() {
        Dialog CustomDialog_WGSSet = new Dialog(this);
        CustomDialog_WGSSet.setContentView(R.layout.dialog_maplevel_wgslocation);

        ExtraResources.setCustomDialogSize(CustomDialog_WGSSet, 0.8f,0.4f);

        Button Accept = CustomDialog_WGSSet.findViewById(R.id.WGSDialog_Accept);
        Button Cancel = CustomDialog_WGSSet.findViewById(R.id.WGSDialog_Cancel);
        EditText WGSLatitude = CustomDialog_WGSSet.findViewById(R.id.WGSDialog_latitude);
        EditText WGSLongitude = CustomDialog_WGSSet.findViewById(R.id.WGSDialog_Longitude);

        WGSLatitude.setText(String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude));
        WGSLongitude.setText(String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().longitude));

        Accept.setOnClickListener(v -> {
            double LatitudeValue = Double.parseDouble(WGSLatitude.getText().toString());
            double LongitudeValue = Double.parseDouble(WGSLongitude.getText().toString());

            if((LatitudeValue >= 33.0 && LongitudeValue >=124.0) && (LatitudeValue <=38.0 && LongitudeValue <=130.0)) {
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(LatitudeValue, LongitudeValue), true);
            } else {
                Toast.makeText(Activity_Example_MapLevel.this, R.string.maplevel_dialog_UnAccessable, Toast.LENGTH_SHORT).show();
            }
            CustomDialog_WGSSet.dismiss();
        });
        Cancel.setOnClickListener(v -> CustomDialog_WGSSet.dismiss());

        CustomDialog_WGSSet.show();
    }

    private void ZoomlevelAndWGS() {
        Dialog CustomDialog_ZoomWGS = new Dialog(this);
        CustomDialog_ZoomWGS.setContentView(R.layout.dialog_maplevel_zoomwgs);

        ExtraResources.setCustomDialogSize(CustomDialog_ZoomWGS, 0.8f, 0.5f);

        Button Accept = CustomDialog_ZoomWGS.findViewById(R.id.ZoomWGS_Accept);
        Button Cancel = CustomDialog_ZoomWGS.findViewById(R.id.ZoomWGS_Cancel);
        EditText WGSLatitude = CustomDialog_ZoomWGS.findViewById(R.id.ZoomWGS_Latitude);
        EditText WGSLongitude = CustomDialog_ZoomWGS.findViewById(R.id.ZoomWGS_Longitude);
        EditText Zoomlevel = CustomDialog_ZoomWGS.findViewById(R.id.ZoomWGS_Zoomlevel);

        WGSLatitude.setText(String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().latitude));
        WGSLongitude.setText(String.valueOf(mapView.getMapCenterPoint().getMapPointGeoCoord().longitude));
        Zoomlevel.setText(String.valueOf(mapView.getZoomLevel()));

        Accept.setOnClickListener(v -> {
            double LatitudeValue = Double.parseDouble(WGSLatitude.getText().toString());
            double LongitudeValue = Double.parseDouble(WGSLongitude.getText().toString());
            int ZoomlevelValue = Integer.parseInt(Zoomlevel.getText().toString());

            if((LatitudeValue >= 33.0 && LongitudeValue >=124.0) && (LatitudeValue <=38.0 && LongitudeValue <=130.0) && (ZoomlevelValue >= 1 && ZoomlevelValue <= 10)) {
                mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(LatitudeValue, LongitudeValue), ZoomlevelValue, true);
            } else {
                Toast.makeText(Activity_Example_MapLevel.this, R.string.maplevel_dialog_UnAccessable2, Toast.LENGTH_SHORT).show();
            }
            CustomDialog_ZoomWGS.dismiss();
        });
        Cancel.setOnClickListener(v -> CustomDialog_ZoomWGS.dismiss());

        CustomDialog_ZoomWGS.show();

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        // 좌표 위치는 서울역: Google Map의 WGS84 제공좌표 기준
        // 기본 줌레벨: 2
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.555145,126.970788), 2, true);
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

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
