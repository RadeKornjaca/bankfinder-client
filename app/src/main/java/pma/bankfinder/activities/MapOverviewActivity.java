package pma.bankfinder.activities;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import pma.bankfinder.R;
import pma.bankfinder.activities.decorators.ToolbarDecorator;
import pma.bankfinder.fragments.LocationUnavailableDialogFragment;
import pma.bankfinder.services.MapSyncIntentService;

public class MapOverviewActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = MapOverviewActivity.class.getSimpleName();

    private GoogleMap mMap;

    private LocationManager mLocationManager;
    private LocationListener mLocationListener = new LocationListener() {
        private final String TAG = LocationListener.class.getSimpleName();

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private Location mLocation;

    private static final int LOCATION_REFRESH_TIME = 0;
    private static final int LOCATION_REFRESH_DISTANCE = 0;

    private static final int ZOOM_LEVEL = 15;

    // Used for back button behavior override

    private Toast toast;
    private boolean doubleBackToExitPressedOnce = false;
    private static final int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_overview);

        ToolbarDecorator toolbarDecorator = new ToolbarDecorator(this, findViewById(R.id.activity_map_overview_id), R.id.toolbar, "Freefinder");
        toolbarDecorator.decorate();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        String locationProvider = mLocationManager.getBestProvider(criteria, true);

        try {
            mLocation = mLocationManager.getLastKnownLocation(locationProvider);
        } catch(SecurityException se) {
            DialogFragment locationUnavailableDialogFragment = new LocationUnavailableDialogFragment();
            locationUnavailableDialogFragment.show(getSupportFragmentManager(), "location_unavailable");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    mLocationListener
            );
        } catch(SecurityException se) {
            DialogFragment locationUnavailableDialogFragment = new LocationUnavailableDialogFragment();
            locationUnavailableDialogFragment.show(getSupportFragmentManager(), "location_unavailable");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. Marker is added
     * on the current user location.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentLocation = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

        mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, ZOOM_LEVEL));

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();

                MapSyncIntentService.startActionFetchLocations(getApplicationContext(), visibleRegion.farLeft, visibleRegion.nearRight);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_category_list:
                Intent categoriesIntent = new Intent(this, CategoryListActivity.class);
                startActivity(categoriesIntent);

                return true;

            case R.id.app_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            toast.cancel();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        toast = Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT);
        toast.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, DELAY_TIME);
    }
}
