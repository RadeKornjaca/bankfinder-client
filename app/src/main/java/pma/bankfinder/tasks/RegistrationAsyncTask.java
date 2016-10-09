package pma.bankfinder.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import pma.bankfinder.rest.PostMethod;

/**
 * Created by rade on 5.10.16..
 */
public class RegistrationAsyncTask extends AsyncTask<PostMethod, Integer, JSONObject> {

    @Override
    protected JSONObject doInBackground(PostMethod... postMethod) {
        postMethod[0].sendRequest();
        JSONObject JSONResponse = postMethod[0].responseSingle();

        return JSONResponse;
    }

    protected void onPostExecute(JSONObject JSONResponse) {
        Log.d("User registration:", JSONResponse.toString());
    }
}
