package com.example.laylaiftikhar.mymapz;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Layla Iftikhar on 2/23/2017.
 */

public class ReportCrime extends AsyncTask<Void, Void, Void> {
    static int responseCode;
    public StringBuffer response;
    public String urlParameters;
    public DataOutputStream wr;
    public String changedResponse;
    public static String apitoken, crime_type, crime_location, latitude, longitude, crime_time, crime_date, crime_detail, statusreport, allreports;

    public void reportcrime() {

       apitoken= Login.token;
        crime_type= CrimeDrawerActivity.crime;
        latitude= Double.toString(MapsDrawerActivity.latitude);
        longitude= Double.toString(MapsDrawerActivity.longitude);
        crime_time= CrimeDrawerActivity.reportingtime;
        crime_date= CrimeDrawerActivity.reportingdate;
        crime_location= MapsDrawerActivity.finalLocation;
        crime_detail= "no details";



        this.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }
    @Override
    protected Void doInBackground(Void... params) {
        final String TAG="ERROR TEXT MESSAGE";

        try {

            String url = "http://r-cube.tk/api/report_crime";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            //urlParameters = "cell="+cellc+"&cell_auth_code"+otp1c+"email_auth_code"+otp2c;
            urlParameters = "api_token="+apitoken+"&crime_type="+crime_type+"&crime_location="+crime_location+"&latitude="+latitude+"&longitude="+longitude+ "&crime_time="+crime_date+ "&crime_detail="+crime_detail;


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
            //print result
            changedResponse = response.toString();
            Log.d(TAG, "HELLO THIS IS THE RESPONSE" +changedResponse);
            JSONObject jsonObj = new JSONObject(changedResponse);
            statusreport    = jsonObj.getString("status");
            Log.d(TAG, "HELLO this is the status" +statusreport);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
