package pma.bankfinder.adapters;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.activeandroid.ActiveAndroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pma.bankfinder.R;
import pma.bankfinder.database.model.Category;
import pma.bankfinder.rest.GetMethod;
import pma.bankfinder.rest.Parameter;
import pma.bankfinder.rest.Request;

/**
 * Created by rade on 18.9.16..
 */
public class CategorySyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver mContentResolver;

    public CategorySyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public CategorySyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();

    }

    @Override
    public void onPerformSync(Account account,
                              Bundle bundle,
                              String s,
                              ContentProviderClient contentProviderClient,
                              SyncResult syncResult) {

        String apiUrl = getContext().getString(R.string.freefinder_api_url);

        List<Parameter> parameters = new ArrayList<Parameter>();    // Add newest timestamp from app's database

        Request request = new Request(apiUrl, "categories", parameters);
        GetMethod categoriesGetMethod = new GetMethod(getContext(), request);

        JSONArray responseJSON = categoriesGetMethod.responseCollection();

        ActiveAndroid.beginTransaction();
        try {
            for(int i = 0;i < responseJSON.length();i++) {
                try {
                    JSONObject categoryJSON = responseJSON.getJSONObject(i);

                    String name = (String)categoryJSON.get("name");
                    int categoryId = (Integer)categoryJSON.get("category_id");
                    Category parentCategory = Category.load(Category.class, categoryId);

                    Category category = new Category(name, parentCategory);
                    category.save();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }


}
