package pma.bankfinder.rest;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import pma.bankfinder.R;

/**
 * Created by rade on 6/19/16.
 */
public class GetMethod extends AbstractRequestMethod {
    private static final String TAG = GetMethod.class.getSimpleName();

    public void sendRequest(LatLng upperLeft, LatLng downRight) {
        try {
            String link = "http://10.42.0.1:4567/places";
            link = addParameter(link, "top_lat", String.valueOf(upperLeft.latitude));
            link = addParameter(link, "top_long", String.valueOf(upperLeft.longitude));
            link = addParameter(link, "down_lat", String.valueOf(downRight.latitude));
            link = addParameter(link, "down_long", String.valueOf(downRight.longitude));

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Log.d(TAG, connection.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String addParameter(String URL, String key, String value) {
        StringBuilder parametrizedURL = new StringBuilder(URL);

        String endToken = URL.contains("?") ? "&" : "?";
        parametrizedURL.append(endToken);

        parametrizedURL.append(key + "=" + value);

        return parametrizedURL.toString();
    }
}
