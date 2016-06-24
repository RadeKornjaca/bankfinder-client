package pma.bankfinder.activities;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import pma.bankfinder.R;
import pma.bankfinder.fragments.LocationUnavailableDialogFragment;
import pma.bankfinder.services.MapSyncIntentService;

public class MapOverviewActivity extends FragmentActivity implements OnMapReadyCallback {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_overview);
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
                Intent fetchLocationsIntent = new Intent(MapOverviewActivity.this, MapSyncIntentService.class);

                VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();

                fetchLocationsIntent.setAction(MapSyncIntentService.ACTION_FETCH_LOCATIONS);
                fetchLocationsIntent.putExtra(MapSyncIntentService.UPPER_LEFT_COORDINATE, visibleRegion.nearLeft);
                fetchLocationsIntent.putExtra(MapSyncIntentService.DOWN_RIGHT_COORDINATE, visibleRegion.farRight);

                startService(fetchLocationsIntent);
            }
        });

    }

}
