package pma.bankfinder.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import pma.bankfinder.R;
import pma.bankfinder.rest.GetMethod;
import pma.bankfinder.rest.Method;
import pma.bankfinder.rest.Request;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BankSyncIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FETCH_BANKS = "pma.bankfinder.services.action.FETCH_BANKS";
    private static final String ACTION_BAZ = "pma.bankfinder.services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "pma.bankfinder.services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "pma.bankfinder.services.extra.PARAM2";

    public BankSyncIntentService() {
        super("BankSyncIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFetchBanks(Context context) {
        Intent intent = new Intent(context, BankSyncIntentService.class);
        intent.setAction(ACTION_FETCH_BANKS);

        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, BankSyncIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_BANKS.equals(action)) {;
                handleActionFetchBanks();
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchBanks() {
        String apiURL = getApplicationContext().getString(R.string.freefinder_api_url);
        String resource = "categories";

        Request request = new Request(apiURL, resource, null);
        GetMethod getRequest = new GetMethod(getApplicationContext(), request);

        getRequest.responseCollection();
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
