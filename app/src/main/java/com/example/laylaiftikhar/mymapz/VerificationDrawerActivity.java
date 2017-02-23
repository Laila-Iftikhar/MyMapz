package com.example.laylaiftikhar.mymapz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.laylaiftikhar.mymapz.LoginActivity.phone;

public class VerificationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    final Context context = this;
    private Button button;
    //this is the number wala et
    private EditText resultt,  remail, rpassword, rname, confirmpassword;
    public static String otp, otp2;
    int  randomPIN;
    int validated;
    String urlParameters;
    int responseCode;
    StringBuffer response;

    InputStream is=null;
    String result=null;
    String line=null;
    String text="";
    public static String name,cell, email, password;
    String responseBody;
    int code;
    static int responseCode2;

    private Register register;
    private Verify verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_drawer);

        button = (Button) findViewById(R.id.btn_code);
        resultt = (EditText) findViewById(R.id.input_phone);
        remail=(EditText) findViewById(R.id.input_email);
        rpassword= (EditText)findViewById(R.id.input_password);
        rname= (EditText) findViewById(R.id.input_name);
        confirmpassword= (EditText) findViewById(R.id.input_confirmpassword);
        button.setOnClickListener(this);
        name=rname.getText().toString();
        email= remail.getText().toString();
        cell= resultt.getText().toString();
        password= rpassword.getText().toString();







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        getMenuInflater().inflate(R.menu.verification_drawer, menu);
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
            startActivity(new Intent(this, MapsDrawerActivity.class));
        } else if (id == R.id.nav_report) {
            startActivity(new Intent(this, MapsDrawerActivity.class));

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
        name=rname.getText().toString();
        email= remail.getText().toString();
        cell= resultt.getText().toString();
        password= rpassword.getText().toString();
        if (confirmpassword.getText().toString().equals(password )){
            register = new Register();

            register.sendPost();


            Toast.makeText(getApplicationContext(), "pin "+randomPIN,
                Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Response Code"+register.responseCode,
                    Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "uri parameters"+register.urlParameters,
                    Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(), "new response"+register.changedResponse,
                    Toast.LENGTH_LONG).show();


        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
            final EditText userInput2 = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput2);
        final TextView timer = (TextView) promptsView.findViewById(R.id.textView2);
        new CountDownTimer(90000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                timer.setText("OTP Expired please try again");
            }

        }.start();

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                otp= userInput.getText().toString();
                                otp2= userInput2.getText().toString();
                                verify = new Verify();

                                verify.Verifyy();
                                Toast.makeText(getApplicationContext(), "Response Code"+verify.responseCode,
                                        Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "uri parameters"+verify.urlParameters,
                                        Toast.LENGTH_LONG).show();

                                Toast.makeText(getApplicationContext(), "new response"+verify.changedResponse,
                                        Toast.LENGTH_LONG).show();



                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();}
        else
        {
            Toast.makeText(getApplicationContext(), "Both passwords do not match",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void generatePIN()
    {
        //generate a 4 digit integer 1000 <10000
        randomPIN = (int)(Math.random()*9000)+1000;


    }
    public void validatePin(){


        if(Integer.toString(randomPIN).equals(otp)){
            validated=1;
            startActivity(new Intent(this, MapsDrawerActivity.class));
        }
        else{
            validated=0;
            Toast.makeText(getApplicationContext(), "Wrong OTP, Please Try again",
                    Toast.LENGTH_LONG).show();

        }

    }







}

