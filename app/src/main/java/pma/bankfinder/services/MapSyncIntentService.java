package pma.bankfinder.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pma.bankfinder.R;
import pma.bankfinder.rest.GetMethod;
import pma.bankfinder.rest.Method;
import pma.bankfinder.rest.Parameter;
import pma.bankfinder.rest.Request;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MapSyncIntentService extends IntentService {

    public static final String ACTION_FETCH_LOCATIONS = "pma.bankfinder.services.action.FETCH_LOCATIONS";
    public static final String ACTION_ADD_LOCATIONS = "pma.bankfinder.services.action.ADD_LOCATIONS";

    public static final String UPPER_LEFT_COORDINATE = "pma.bankfinder.services.extra.UPPER_LEFT_COORDINATE";
    public static final String DOWN_RIGHT_COORDINATE = "pma.bankfinder.services.extra.DOWN_RIGHT_COORDINATE";

    public MapSyncIntentService() {
        super("MapSyncIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFetchLocations(Context context, LatLng upperLeft, LatLng downRight) {
        Intent intent = new Intent(context, MapSyncIntentService.class);
        intent.setAction(ACTION_FETCH_LOCATIONS);
        intent.putExtra(UPPER_LEFT_COORDINATE, upperLeft);
        intent.putExtra(DOWN_RIGHT_COORDINATE, downRight);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionAddLocations(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MapSyncIntentService.class);
        intent.setAction(ACTION_ADD_LOCATIONS);
//        intent.putExtra(EXTRA_PARAM1, param1);
//        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_LOCATIONS.equals(action)) {
                final LatLng upperLeftCoordinates = intent.getParcelableExtra(UPPER_LEFT_COORDINATE);
                final LatLng downRightCoordinates = intent.getParcelableExtra(DOWN_RIGHT_COORDINATE);
                handleActionFetchLocations(upperLeftCoordinates, downRightCoordinates);
            } else if (ACTION_ADD_LOCATIONS.equals(action)) {
//                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
//                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
//                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchLocations(LatLng upperLeftCoordinates, LatLng downRightCoordinates) {
        String resource = "places";

        List<Parameter> parameters = new ArrayList<Parameter>();

        parameters.add(new Parameter("top_lat", String.valueOf(upperLeftCoordinates.latitude)));
        parameters.add(new Parameter("top_long", String.valueOf(upperLeftCoordinates.longitude)));
        parameters.add(new Parameter("down_lat", String.valueOf(downRightCoordinates.latitude)));
        parameters.add(new Parameter("down_long", String.valueOf(downRightCoordinates.longitude)));

        Request request = new Request(getString(R.string.freefinder_api_url), resource, parameters);
        GetMethod getRequest = new GetMethod(getApplicationContext(), request);

        getRequest.sendRequest();

        JSONArray response;

        try {
            response = getRequest.response().getJSONArray("places");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
