package pma.bankfinder.rest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by rade on 16.9.16..
 */
public class PostMethod extends AbstractRequestMethod {
    private static final String TAG = GetMethod.class.getSimpleName();

    private String rootElement;

    public PostMethod(Context context, Request request, String rootElement) {
        super(context, request);
        this.rootElement = rootElement;
    }

    @Override
    public String sendRequest() {
        String responseText = null;

        try {
            URL url = this.buildURL();
            HttpURLConnection connection = super.createConnection(url);

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

            // byte[] byteParameters = this.getParametersAsJSON().toString().getBytes("UTF-8");
            out.write(this.getParametersAsJSON().toString());
            out.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

            InputStream resultStream = new BufferedInputStream(connection.getInputStream());

            responseText = getResponseText(resultStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseText;
    }

    private JSONObject getParametersAsJSON() {
        JSONObject objectJSON = new JSONObject();
        JSONObject parametersJSON = new JSONObject();

        try {
            for(Parameter parameter : request.getParameters()) {
                parametersJSON.put(parameter.getFieldName(), parameter.getFieldValue());
            }

            objectJSON.put(rootElement, parametersJSON);

            Log.d("Reg params:", objectJSON.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return objectJSON;
    }
}
