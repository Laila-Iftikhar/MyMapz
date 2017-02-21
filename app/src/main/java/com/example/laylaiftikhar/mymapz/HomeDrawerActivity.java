package com.example.laylaiftikhar.mymapz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
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
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    InputStream is=null;
    String result=null;
    String line=null;
    int code;
    Button loginButton;
    TextView signup;
    EditText et_username, et_password;
    public static String phone, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        signup = (TextView) findViewById(R.id.link_signup);
        signup.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        et_username= (EditText) findViewById(R.id.input_email);
        et_password= (EditText) findViewById(R.id.input_password);
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
        getMenuInflater().inflate(R.menu.home_drawer, menu);
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
           // startActivity(new Intent(this, HomeDrawerActivity.class));
        } else if (id == R.id.nav_report) {
            //startActivity(new Intent(this, MapsDrawerActivity.class));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

// Set up the input
            final String input = "Please Sign in to continue!";
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text


// Set up the buttons
            builder.setTitle(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.show();

        } else if (id == R.id.nav_locations) {
           // startActivity(new Intent(this, MyLocationsActivity.class));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

// Set up the input
            final String input = "Please Sign in to continue!";
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text


// Set up the buttons
            builder.setTitle(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.show();

        } else if (id == R.id.nav_myreports) {
            //startActivity(new Intent(this, MyReportsActivity.class));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

// Set up the input
            final String input = "Please Sign in to continue!";
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text


// Set up the buttons
            builder.setTitle(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.show();

        } else if (id == R.id.nav_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

// Set up the input
            final String input = "Please Sign in to continue!";
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text


// Set up the buttons
            builder.setTitle(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.show();

        } else if (id == R.id.nav_logout) {
            //startActivity(new Intent(this, HomeDrawerActivity.class));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

// Set up the input
            final String input = "Please Sign in to continue!";
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text


// Set up the buttons
            builder.setTitle(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            builder.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void insert()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("phone",phone));
        nameValuePairs.add(new BasicNameValuePair("password",password));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.1.5:8080/select.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(getBaseContext(), "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                if(et_username.getText().toString().equals("03007252112")&&et_password.getText().toString().equals("laila")){
                startActivity(new Intent(this, MapsDrawerActivity.class));}
                else{
                    Toast.makeText(getBaseContext(), "Wrong Credentials, login failed",
                            Toast.LENGTH_LONG).show();
                }
                //break;
                phone = et_username.getText().toString();
                password = et_password.getText().toString();
                break;
                //insert();
            }
            case R.id.link_signup: {
                startActivity(new Intent(this, VerificationDrawerActivity.class));


                break;
            }

        }
    }


}
