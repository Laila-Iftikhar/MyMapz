package com.example.laylaiftikhar.mymapz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.laylaiftikhar.mymapz.R.id.button;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    public static double latitude, longitude;
    public static String fcityName, fcountryName,fstateName, fknownName;
    public static String finalLocation;
    public static ArrayList<String> savedLocations = new ArrayList<String>();
    Button regionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        regionbutton = (Button) findViewById(R.id.button);
        regionbutton.setOnClickListener(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)   {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));

        // Enable MyLocation Layer of Google Map
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);


                // Get LocationManager object from System Service LOCATION_SERVICE
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                // Create a criteria object to retrieve provider
                Criteria criteria = new Criteria();

                // Get the name of the best provider
            if (locationManager != null) {
                String provider = locationManager.getBestProvider(criteria, true);

                // Get Current Location
                Location myLocation = locationManager.getLastKnownLocation(provider);

                // set map type
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                // Get latitude of the current location
                if (myLocation != null) {
                    latitude = myLocation.getLatitude();

                    // Get longitude of the current location
                    longitude = myLocation.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    List<Address> addresses=null;


                    try {

                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        fcityName = addresses.get(0).getAddressLine(0);
                        fstateName = addresses.get(0).getAddressLine(1);
                        fcountryName = addresses.get(0).getAddressLine(2);
                        fknownName = addresses.get(0).getFeatureName();


                        finalLocation= fknownName+", "+fstateName+", "+fcountryName;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            }
            // Create a LatLng object for the current location


            // Show the current location in Google Map


            // Zoom in the Google Map
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

            return;

            
        }






    }


    @Override
    public void onClick(View v) {

                startActivity(new Intent(this, CrimeDrawerActivity.class));

            }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}



