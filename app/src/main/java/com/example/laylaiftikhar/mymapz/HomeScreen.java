package com.example.laylaiftikhar.mymapz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    Button bGo;
    TextView textt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);
        textt = (TextView) findViewById(R.id.tvGo);
        bGo = (Button) findViewById(R.id.bGo);
        bGo.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bGo:{
                startActivity(new Intent(this, MapsActivity.class));
                break;}



        }
    }
}
