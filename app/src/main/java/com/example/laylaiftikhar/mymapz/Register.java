package com.example.laylaiftikhar.mymapz;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Layla Iftikhar on 2/20/2017.
 */

public class Register extends AsyncTask<Void, Void, Void> {
    static int responseCode;
    public StringBuffer response;
    public String urlParameters;
    public DataOutputStream wr;
    public String changedResponse;
    public static String namec, emailc, cellc, passwordc, statusregister;

    public void sendPost() {
        namec=VerificationDrawerActivity.name;
        emailc=VerificationDrawerActivity.email;
        cellc=VerificationDrawerActivity.cell;
        passwordc= VerificationDrawerActivity.password;

         this.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }

    @Override
    protected Void doInBackground(Void... params) {
        final String TAG="ERROR TEXT MESSAGE";

        try {

            String url = "http://r-cube.tk/api/register";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            urlParameters = "name="+namec+"&email="+emailc+"&cell="+cellc+"&password="+passwordc;
            //urlParameters = "name=Ali&email=13beselchaudhry@seecs.edu.pk&cell=03369177747&password=123456";


            // Send post request
            con.setDoOutput(true);
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            responseCode = con.getResponseCode();

            Log.d(TAG, "HELLO THIS IS THE ERROR" + url + urlParameters + responseCode);


            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                // entity=response.getEntity;
            }
            in.close();
            changedResponse = response.toString();
            Log.d(TAG, "HELLO THIS IS THE RESPONSE" +changedResponse);
            JSONObject jsonObj = new JSONObject(changedResponse);
            statusregister    = jsonObj.getString("token");
            Log.d(TAG, "HELLO THIS IS THE register status VALUE" + statusregister);
            //print result

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
