package com.example.laylaiftikhar.mymapz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
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
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);


        signup = (TextView) findViewById(R.id.link_signup);
        signup.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        et_username= (EditText) findViewById(R.id.input_email);
        et_password= (EditText) findViewById(R.id.input_password);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                startActivity(new Intent(this, MapsDrawerActivity.class));
                //break;
                phone = et_username.getText().toString();
                password = et_password.getText().toString();
                break;
                //insert();
            }
            case R.id.link_signup: {
                startActivity(new Intent(this, VerificationActivity.class));


                break;
            }

        }
    }
    protected int getLayoutId() {
        return R.layout.activity_login;
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

}
