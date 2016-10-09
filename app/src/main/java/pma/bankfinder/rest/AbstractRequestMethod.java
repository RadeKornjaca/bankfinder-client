package pma.bankfinder.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import pma.bankfinder.R;

/**
 * Created by rade on 6/19/16.
 */
public abstract class AbstractRequestMethod {

    private Context context;
    protected Request request;

    public AbstractRequestMethod(Context context, Request request) {
        this.context = context;
        this.request = request;
    }

    public abstract String sendRequest();

    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString(context.getString(R.string.settings_access_token), "");

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Content-Type", "application/json");
        // connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Token " + token);

        return connection;
    }

    public URL buildURL() throws MalformedURLException {
        StringBuilder URLStringBuilder = new StringBuilder();

        URLStringBuilder.append(request.getApiURL());
        URLStringBuilder.append("/");
        URLStringBuilder.append(request.getResource());

        return new URL(URLStringBuilder.toString());
    }

    public JSONObject responseSingle() {
        JSONObject JSONresult = new JSONObject();

        try {
            String responseText = this.sendRequest();
            JSONresult = new JSONObject(responseText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JSONresult;
    }

    public JSONArray responseCollection() {
        JSONArray JSONresult = new JSONArray();

        try {
            String responseText = this.sendRequest();
            JSONresult = new JSONArray(responseText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JSONresult;
    }

    protected static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
