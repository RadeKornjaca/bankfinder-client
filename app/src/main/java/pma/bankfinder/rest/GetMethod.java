package pma.bankfinder.rest;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pma.bankfinder.R;

/**
 * Created by rade on 6/19/16.
 */
public class GetMethod extends AbstractRequestMethod {
    private static final String TAG = GetMethod.class.getSimpleName();

    public void sendRequest(Request request) {
        try {
            URL url = request.buildURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Log.d(TAG, connection.getResponseMessage());

            InputStream resultStream = new BufferedInputStream(connection.getInputStream());

            try {
                JSONArray JSONresult = new JSONArray(getResponseText(resultStream));
                Log.d(TAG, JSONresult.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    private static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
