package com.example.laylaiftikhar.mymapz;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegionActivity extends AppCompatActivity implements View.OnClickListener{
    public static double latitude;
    public static double longitude;
    public static String cityName, countryName,stateName, knownName;
    public static String finalLocation;
    public static ArrayList<String> savedLocations = new ArrayList<String>();
    private TextView locationTextView;
    Button toscrroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region2);
        latitude= MapsActivity.latitude;
        longitude=MapsActivity.longitude;
        locationTextView=(TextView)findViewById(R.id.textViewAddress);
        toscrroll=(Button) findViewById(R.id.btn_saveLocation) ;
        toscrroll.setOnClickListener(this);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses=null;


        try {

            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            cityName = addresses.get(0).getAddressLine(0);
            stateName = addresses.get(0).getAddressLine(1);
            countryName = addresses.get(0).getAddressLine(2);
            knownName = addresses.get(0).getFeatureName();

            locationTextView.setText(knownName+", "+stateName+", "+countryName);
            finalLocation= knownName+", "+stateName+", "+countryName;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveLocations(){
        savedLocations.add(finalLocation);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_saveLocation: {
                startActivity(new Intent(this,ScrollingActivity.class));
                break;
            }


        }

    }
}
