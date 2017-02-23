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
 * Created by Layla Iftikhar on 2/22/2017.
 */

public class SaveLocations extends AsyncTask<Void, Void, Void> {
    static int responseCode;
    public StringBuffer response;
    public String urlParameters;
    public DataOutputStream wr;
    public String changedResponse;
    public static String apitoken, mylocations, statuslocations;

    public void savelocations() {
        apitoken= Login.token;

       // String[] arrays= MapsDrawerActivity.savedLocations.toArray(new String[0]);
        mylocations= MapsDrawerActivity.finalLocation;


        this.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }

    @Override
    protected Void doInBackground(Void... params) {
        final String TAG="ERROR TEXT MESSAGE";

        try {

            String url = "http://r-cube.tk/api/store_userlocation";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            urlParameters = "api_token="+apitoken+"&user_locations="+mylocations;
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
            //print result
            changedResponse = response.toString();



            Log.d(TAG, "HELLO THIS IS THE STATUS" + changedResponse);
            JSONObject jsonObj = new JSONObject(changedResponse);
            statuslocations    = jsonObj.getString("status");
            Log.d(TAG, "HELLO THIS IS THE JSONOBJECT VALUE" + statuslocations);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
