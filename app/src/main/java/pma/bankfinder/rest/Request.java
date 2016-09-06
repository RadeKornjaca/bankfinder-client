package pma.bankfinder.rest;

import android.content.res.Resources;

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
    private Method method;

    public Request(String apiURL, String resource, List<Parameter> parameters, Method method) {
        this.apiURL = apiURL;
        this.resource = resource;
        this.parameters = parameters;
        this.method = method;
    }

    public URL buildURL() throws MalformedURLException {
        StringBuilder URLStringBuilder = new StringBuilder();

        URLStringBuilder.append(apiURL);
        URLStringBuilder.append("/");
        URLStringBuilder.append(resource);

        if(this.method == Method.GET && parameters != null) {
            URLStringBuilder.append("?");

            String delimiter = "";
            for(Parameter parameter : parameters) {
                URLStringBuilder.append(delimiter).
                        append(parameter.getFieldName() + "=" + parameter.getFieldValue());
                delimiter = "&";
            }

        }

        return new URL(URLStringBuilder.toString());
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

    public Method getMethod() {
        return method;
    }
}
