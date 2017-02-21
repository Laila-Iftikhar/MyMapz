package com.example.laylaiftikhar.mymapz;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Layla Iftikhar on 2/21/2017.
 */

public class HTTPPostActivity  {


    public static void makePostRequest() {


        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost("http://r-cube.tk/api/register");


        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
        nameValuePair.add(new BasicNameValuePair("name", "laila"));
        nameValuePair.add(new BasicNameValuePair("email", "laylaiftikhar@hotmail.com"));
        nameValuePair.add(new BasicNameValuePair("cell", "03007252112"));
        nameValuePair.add(new BasicNameValuePair("password", "12345"));



        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }

    }

}
