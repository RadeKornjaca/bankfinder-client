package pma.bankfinder.rest;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pma.bankfinder.R;

/**
 * Class which encapsulates HTTP request used when
 * communicating with the RESTful service
 *
 * Created by rade on 6/29/16.
 */
public class Request {
    private String apiURL;
    private String resource;
    private List<Parameter> parameters;

    public Request(String apiURL, String resource, List<Parameter> parameters) {
        this.apiURL = apiURL;
        this.resource = resource;
        this.parameters = parameters;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getResource() {
        return resource;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

}
