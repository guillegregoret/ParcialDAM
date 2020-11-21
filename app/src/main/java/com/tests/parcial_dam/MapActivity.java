package com.tests.parcial_dam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Random;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    Random rdm = new Random();

    final LatLng DIRECCION1 = new LatLng(rdm.nextFloat(), rdm.nextFloat());
    final LatLng DIRECCION2 = new LatLng(rdm.nextFloat(), rdm.nextFloat());
    /*
    final LatLng DIRECCION1 = new LatLng(-31.620521, -60.696278);
    final LatLng DIRECCION2 = new LatLng(-31.620589, -60.696278);
    */
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }

        googleMap.addMarker(new MarkerOptions()
                .position(DIRECCION1)
                .title("Dirección 1")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        googleMap.addMarker(new MarkerOptions()
                .position(DIRECCION2)
                .title("Dirección 2")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        PolylineOptions line=
                new PolylineOptions().add(DIRECCION1, DIRECCION2)
                        .width(5).color(Color.RED);

        googleMap.addPolyline(line);
        organizarMapa();
    }



    @SuppressLint("MissingPermission")
    private void organizarMapa() {
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DIRECCION2, 16));

    }

}