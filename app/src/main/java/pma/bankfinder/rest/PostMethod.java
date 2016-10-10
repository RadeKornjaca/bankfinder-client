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
    public void sendRequest() {
        String responseText = null;

        try {
            URL url = this.buildURL();
            httpURLConnection = super.createConnection(url);

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            // byte[] byteParameters = this.getParametersAsJSON().toString().getBytes("UTF-8");
            outputStreamWriter.write(this.getParametersAsJSON().toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean isSuccessful() throws IOException{
        return httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED;

    }
}
