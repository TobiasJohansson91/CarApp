package com.example.dervis.autonomous.Activities;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dervis.autonomous.CarRest;
import com.example.dervis.autonomous.Helpers.GsonConv;
import com.example.dervis.autonomous.Helpers.RequestLocation;
import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.example.dervis.autonomous.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class show a google maps fragment with position for user and a marker for the car
 */
public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, RequestLocation.ILocationCallback {

    private RequestLocation requestLocation;
    private GoogleMap mMap;
    private Marker carLocationMarker;
    private LatLng carLocation;
    private LatLng yourLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setHeaderText();

        requestLocation = new RequestLocation(this, this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setHeaderText() {
        String json = getIntent().getStringExtra(MainActivity.HEADER);
        ListObjIcon obj = GsonConv.toObject(json);
        ((TextView)findViewById(R.id.activityTitle)).setText(obj.title);
        ((TextView) findViewById(R.id.activityDescription)).setText(obj.description);
        ((ImageView) findViewById(R.id.activityIcon)).setImageDrawable(ResourceGetter.getDrawable(obj.iconId));
    }

    /**
     * show the location of the car and the user
     *
     * @param googleMap this map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mMap.setMyLocationEnabled(true);
            setMarker();
        }
    }

    /**
     * goes back to previous activity and closes this activity
     * @param view this view
     */
    public void clickBackArrow(View view) {
        finish();
        overridePendingTransition(R.anim.enter_back_anim, R.anim.exit_back_anim);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case RequestLocation.MY_PERMISSION_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    requestLocation.onRequestPermissionsResult(true);
                }
                break;
        }
    }

    @Override
    public void gotLocation(Location location) {
        yourLocation = new LatLng(location.getLatitude(), location.getLongitude());
        moveCarLocationMarker();
        ((TextView) findViewById(R.id.carLocationTextView)).setText(getStreetAddress(carLocation));
        ((TextView) findViewById(R.id.carDistanceTextView)).setText(calculateDistance());
    }

    private String getStreetAddress(LatLng location) {
        Geocoder geocoder = new Geocoder(this);
        String address = "--";
        try {
            List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);
            address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    private String calculateDistance() {
         String distance = "--";
        if (yourLocation != null && carLocation != null) {
            Location A = new Location("");
            A.setLatitude(yourLocation.latitude);
            A.setLongitude(yourLocation.longitude);
            Location B = new Location("");
            B.setLatitude(carLocation.latitude);
            B.setLongitude(carLocation.longitude);
            float dist = A.distanceTo(B) / 1000;
            distance = "" + dist + "km";
        }
        return distance;
    }

    private void setMarker(){
        if (mMap != null && carLocation != null){
            MarkerOptions carOptions = new MarkerOptions().position(carLocation).title("Car #1");
            carLocationMarker = mMap.addMarker(carOptions);
        }
    }

    private void moveCarLocationMarker(){
        carLocation = new LatLng(57.4759073, 12.0952908);
        if (carLocationMarker == null){
            setMarker();
            zoomCameraToCar();
        }
        else {
            carLocationMarker.setPosition(carLocation);
        }

    }

    private void zoomCameraToCar(){
        CameraUpdate center = CameraUpdateFactory.newLatLng(carLocation);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15));
    }
}

