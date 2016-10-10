package pma.bankfinder.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import pma.bankfinder.R;
import pma.bankfinder.activities.MapOverviewActivity;
import pma.bankfinder.rest.PostMethod;
/**
 * Created by rade on 5.10.16..
 */
public class RegistrationAsyncTask extends AsyncTask<PostMethod, Integer, Void> {

    private Context context;

    public RegistrationAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(PostMethod... postMethods) {
        postMethods[0].sendRequest();

        try {
            JSONObject JSONResponse = postMethods[0].response();

            if(postMethods[0].isSuccessful()) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(context.getString(R.string.settings_access_token), JSONResponse.getString("access_token"));
                editor.commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Intent intent = new Intent(context, MapOverviewActivity.class);
        context.startActivity(intent);
    }
}
