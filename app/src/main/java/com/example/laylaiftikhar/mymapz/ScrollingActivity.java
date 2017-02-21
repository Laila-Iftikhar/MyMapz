package com.example.laylaiftikhar.mymapz;

import android.graphics.Region;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {
    TextView locations;
    public static String finalocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        locations= (TextView) findViewById(R.id.savedlocationss);
        locations.setText(RegionActivity.finalLocation);

    }
}
