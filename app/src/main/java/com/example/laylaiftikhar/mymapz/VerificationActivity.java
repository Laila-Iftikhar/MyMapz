package com.example.laylaiftikhar.mymapz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

import static com.example.laylaiftikhar.mymapz.LoginActivity.phone;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {
    final Context context = this;
    private Button button;
    private EditText resultt;
    private String otp;
    int  randomPIN;
    int validated;

    InputStream is=null;
    String result=null;
    String line=null;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        button = (Button) findViewById(R.id.btn_code);
        resultt = (EditText) findViewById(R.id.input_phone);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {// get prompts.xml view

        insert();
        generatePIN();
        Toast.makeText(getApplicationContext(), "pin "+randomPIN,
                Toast.LENGTH_LONG).show();

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
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
                        new OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                otp= userInput.getText().toString();
                                validatePin();
                            }
                        })
                .setNegativeButton("Cancel",
                        new OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
    public void insert()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("phone",phone));


        try
        {
            HttpClient Httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("ftp://%2520r-cube%2540aliazlan.com@aliazlan.com/app/Http/Api/API.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = Httpclient.execute(httppost);
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
    public void generatePIN()
    {
        //generate a 4 digit integer 1000 <10000
        randomPIN = (int)(Math.random()*9000)+1000;


    }
    public void validatePin(){
        

        if(Integer.toString(randomPIN).equals(otp)){
            validated=1;
            startActivity(new Intent(this, MapsActivity.class));
        }
        else{
            validated=0;
            Toast.makeText(getApplicationContext(), "Wrong OTP, Please Try again",
                    Toast.LENGTH_LONG).show();

        }

    }
}




