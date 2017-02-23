package com.example.laylaiftikhar.mymapz;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsDrawerActivity extends FragmentActivity
        implements OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private GoogleMap mMap;
    public static double latitude, longitude;
    public static String fcityName, fcountryName,fstateName, fknownName, temploc;
    public static String finalLocation;
    public static ArrayList<String> savedLocations = new ArrayList<String>();
    Button regionbutton, saveloc;
    private SaveLocations saveuserlocations;
    private DisplayCrime displaycrime;
    private ReportAllCrimes viewcrimes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_drawer);
        regionbutton = (Button) findViewById(R.id.button);
        regionbutton.setOnClickListener(this);
        saveloc= (Button) findViewById(R.id.savelocations);
        saveloc.setOnClickListener(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maps_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

        } else if (id == R.id.nav_report) {
            startActivity(new Intent(this, CrimeDrawerActivity.class));

        } else if (id == R.id.nav_locations) {
            startActivity(new Intent(this, MyLocationsActivity.class));

        } else if (id == R.id.nav_myreports) {
            startActivity(new Intent(this, MyReportsActivity.class));

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(this, HomeDrawerActivity.class));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
       // startActivity(new Intent(this, CrimeDrawerActivity.class));
        switch (v.getId()) {
            case R.id.button: {
                startActivity(new Intent(this, CrimeDrawerActivity.class));
                //break;

                break;
                //insert();
            }
            case R.id.savelocations: {

                savedLocations.add(finalLocation);
                saveuserlocations= new SaveLocations();
                saveuserlocations.savelocations();
                displaycrime= new DisplayCrime();
                displaycrime.showlocations();

                startActivity(new Intent(this, MyLocationsActivity.class));


                break;
            }

        }}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));

        // Enable MyLocation Layer of Google Map
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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
                    List<Address> addresses = null;


                    //adding markers to maps
                    viewcrimes = new ReportAllCrimes();
                    viewcrimes.showcrimes();
                    int size = ReportAllCrimes.savedcoordinates.size();

                    for (int i = 0; i < size; ++i) {
                        LatLng coordinate = ReportAllCrimes.savedcoordinates.get(i);
                        String crimenamess = ReportAllCrimes.crimenames.get(i);

                        mMap.addMarker(new MarkerOptions()
                                .position(coordinate)
                                .title(crimenamess + (i + 1))
                                .snippet("Snippet" + (i + 1))
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                                .anchor(0.5f, 0.5f));








                    }






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


}

