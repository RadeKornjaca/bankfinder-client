package pma.bankfinder.rest;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rade on 6/19/16.
 */
public class GetMethod extends AbstractRequestMethod {
    private static final String TAG = GetMethod.class.getSimpleName();

    public GetMethod(Context context, Request request) {
        super(context, request);
    }

    @Override
    public URL buildURL() throws MalformedURLException{
        URL superURL = super.buildURL();

        StringBuilder URLStringBuilder = new StringBuilder(superURL.toString());

        URLStringBuilder.append("?");

        String delimiter = "";
        for(Parameter parameter : request.getParameters()) {
            URLStringBuilder.append(delimiter).
                    append(parameter.getFieldName() + "=" + parameter.getFieldValue());
            delimiter = "&";
        }

        return new URL(URLStringBuilder.toString());
    }

    @Override
    public void sendRequest() {
        try {
            URL url = this.buildURL();
            this.httpURLConnection = super.createConnection(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isSuccessful() throws  IOException {
        return httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}
