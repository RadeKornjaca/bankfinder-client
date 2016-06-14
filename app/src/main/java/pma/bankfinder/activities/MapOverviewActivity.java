package pma.bankfinder.activities;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pma.bankfinder.R;
import pma.bankfinder.fragments.LocationUnavailableDialogFragment;

public class MapOverviewActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = MapOverviewActivity.class.getSimpleName();

    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, ZOOM_LEVEL));
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

    private Location location;

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

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        String locationProvider = locationManager.getBestProvider(criteria, true);

        try {
            location = locationManager.getLastKnownLocation(locationProvider);
        } catch(SecurityException se) {
            DialogFragment locationUnavailableDialogFragment = new LocationUnavailableDialogFragment();
            locationUnavailableDialogFragment.show(getSupportFragmentManager(), "location_unavailable");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    locationListener
            );
        } catch(SecurityException se) {
            DialogFragment locationUnavailableDialogFragment = new LocationUnavailableDialogFragment();
            locationUnavailableDialogFragment.show(getSupportFragmentManager(), "location_unavailable");
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, ZOOM_LEVEL));

    }
}
