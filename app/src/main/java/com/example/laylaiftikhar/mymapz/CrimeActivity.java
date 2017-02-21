package com.example.laylaiftikhar.mymapz;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CrimeActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;
    private EditText locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        locations= (EditText) findViewById(R.id.locations);

        btnDisplay.setOnClickListener(this);
        locations.setText(""+MapsActivity.fknownName+", "+MapsActivity.fstateName+", "+MapsActivity.fcountryName);

    }

    @Override
    public void onClick(View v) {




        // get selected radio button from radioGroup
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);

        Toast.makeText(CrimeActivity.this,
                radioSexButton.getText(), Toast.LENGTH_SHORT).show();



    }
}
/*  <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item> */