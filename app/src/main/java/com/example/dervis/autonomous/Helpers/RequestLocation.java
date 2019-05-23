package com.example.dervis.autonomous.Helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class RequestLocation implements ILocation {
    public static final int MY_PERMISSION_REQUEST_LOCATION = 5959;
    private FusedLocationProviderClient locationProvider;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Activity activity;
    private boolean permissionGranted = false;
    private ILocationCallback callback;

    public RequestLocation(Activity activity, ILocationCallback callback) {
        this.callback = callback;
        this.activity = activity;
        setupLocation();
        requestLocationPermission();
        getLocation();
    }

    private void setupLocation() {
        locationProvider = LocationServices.getFusedLocationProviderClient(activity);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(locationResult != null){
                    callback.gotLocation(locationResult.getLastLocation());
                }
            }
        };
    }

    private void requestLocationPermission() {
        int fineLocation = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocation = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (fineLocation != PackageManager.PERMISSION_GRANTED ||
                coarseLocation != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_REQUEST_LOCATION);
        }else{
            permissionGranted = true;
        }
    }

    public void onRequestPermissionsResult(boolean permissionGranted) {
        if(permissionGranted){
            this.permissionGranted = permissionGranted;
            getLocation();
        }
    }

    private void getLocation() {
        if(permissionGranted) {
            locationProvider.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    public interface ILocationCallback {
        public void gotLocation(Location location);
    }
}

interface ILocation {
    public void onRequestPermissionsResult(boolean permissionGranted);
}