package pma.bankfinder.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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

    protected HttpURLConnection httpURLConnection;

    public AbstractRequestMethod(Context context, Request request) {
        this.context = context;
        this.request = request;
    }

    /**
     * Forms an HTTP request in concrete HTTP request class
     * and sends it to an URL that is obtained from Request
     * object.
     *
     */
    public abstract void sendRequest();

    /**
     * Depending on a concrete HTTP request type, declares
     * if the request was accepted or rejected on the server
     * side.
     *
     * @throws IOException
     */
    public abstract boolean isSuccessful() throws IOException;

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

    /**
     * Transforms a server response from stream to JSON Object.
     *
     * @return JSON Object representing resource(s) from server response
     * @throws JSONException
     * @throws IOException
     */
    public JSONObject response() throws JSONException, IOException {
        InputStream resultStream = new BufferedInputStream(httpURLConnection.getInputStream());
        String responseText = getResponseText(resultStream);

        return new JSONObject(responseText);
    }

    protected static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
