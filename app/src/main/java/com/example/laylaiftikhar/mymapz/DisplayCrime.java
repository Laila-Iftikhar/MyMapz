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

/**
 * Created by Layla Iftikhar on 2/24/2017.
 */

public class DisplayCrime extends AsyncTask<Void, Void, Void> {
    static int responseCode;
    public StringBuffer response;
    public String urlParameters;
    public DataOutputStream wr;
    public String changedResponse;
    public static String cellc, emailc, apitoken, statusdisplaylocations, locations;

    public void showlocations() {
        apitoken= Login.token;




        this.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }
    @Override
    protected Void doInBackground(Void... params) {
        final String TAG="ERROR TEXT MESSAGE";

        try {

            String url = "http://r-cube.tk/api/get_user_location";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            //urlParameters = "cell="+cellc+"&cell_auth_code"+otp1c+"email_auth_code"+otp2c;
            urlParameters = "api_token="+apitoken;


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
            statusdisplaylocations    = jsonObj.getString("status");
            Log.d(TAG, "HELLO THIS IS THE SHOW LOCATIONS STATUS VALUE" + statusdisplaylocations);

            JSONArray jarray = jsonObj.getJSONArray("data");
            //allreports =  jarray.getJSONObject(0).getString("user_locations");
            //Log.d(TAG, "HELLO these are the locations" +allreports);

            for (int i = 0; i < jarray.length(); ++i) {
                JSONObject rec = jarray.getJSONObject(i);

                 locations = rec.getString("user_locations");
                Log.d(TAG, "HELLO these are the locations" +locations);
                MapsDrawerActivity.savedLocations.add(locations);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}