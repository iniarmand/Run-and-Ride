package com.armand17.runandride;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.widget.Chronometer;
import android.widget.TextView;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;


import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;

import android.graphics.Color;
import android.location.Location;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.armand17.runandride.DBHandler.LocalDBHandler;
import com.armand17.runandride.helper.TimeFormatter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Timer;

import com.facebook.FacebookSdk;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        LocationListener {

    private static final long INTERVAL = 1000 * 5; //5dtk
    private static final long FASTEST_INTERVAL = 1000; //1dtk
    private static final long SMALLEST_DISPLACEMENT = 0; //in meter

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient googleApiClient;
    private Location mLocation;
    private LocationRequest mLocationRequest;
    private boolean statusSesi;
    Marker mMarker;
    Marker startMarker, endMarker;
    LatLng loc, startLoc;
    static final LatLng JOGJA = new LatLng(-7.782940, 110.367073);
    static final LatLng XT_SQUARE = new LatLng(-7.816706, 110.386314);
    ArrayList<String> record;
    ArrayList<LatLng> point;
    protected String mLastUpdateTime;
    ToggleButton btnStart;
    Button btnExcercise;
    TextView textDistance;
    TextView textTime;
    TextView textCallories;
    TextView array_loc;
    double lat, lng;
    long time, startTime, endTime, timeInMiliS;
    long secs, mins, hrs;
    String session_type;
    String newpoint;
    String seconds, minutes, hours, milliseconds;
    float jarak, jarakS;

    Handler customHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        statusSesi = false;
        time = 0;
        startTime = 0;
        endTime = 0;
        timeInMiliS = 0;
        if (!isGooglePlayServiceAvailable()){
            finish();
        }
        createLocationRequest();
        setContentView(R.layout.activity_main);

        //inisialisasi UI
        point = new ArrayList<LatLng>();

        btnStart = (ToggleButton) findViewById(R.id.btnStart);
        btnExcercise = (Button) findViewById(R.id.btnKegiatan);
        textDistance = (TextView) findViewById(R.id.textDistance);
        textTime = (TextView) findViewById(R.id.textTime);
        textCallories = (TextView) findViewById(R.id.textCallories);
        array_loc = (TextView)findViewById(R.id.array_loc);


        // Try to obtain the map from the SupportMapFragment.
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();


        //Action UI button
        btnExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btnExcercise);

                popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        btnExcercise.setText(item.getTitle());
                        Toast.makeText(MainActivity.this, "You Choose: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        btnStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    startSession();
                    startTime = System.currentTimeMillis();
                    customHandler.removeCallbacks(runTimer);
                    customHandler.postDelayed(runTimer, 100);
                } else stopSession();
            }
        });

    }

    private boolean isGooglePlayServiceAvailable() {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status){
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    public void startSession() {
        googleMap.clear();
        point.clear();
        jarak = 0;

        startLocationUpdate();
        time = System.currentTimeMillis()/1000;
        statusSesi = true;
    }

    private void stopSession() {
        statusSesi = false;
        stopLocationUpdate();

        Intent data = new Intent(this,SaveSession.class);
        data.putExtra("session",session_type);
        data.putExtra("jarak",jarak);
        data.putExtra("timeElapsed",time);
        data.putExtra("arrayPoint",point);


        startActivity(data);
    }


    private Runnable runTimer = new Runnable() {
        @Override
        public void run() {
            timeInMiliS = System.currentTimeMillis() - startTime;
            customHandler.postDelayed(this, 100);

            String formattedTime = TimeFormatter.timeFormatter(timeInMiliS);
            textTime.setText("" + formattedTime);
        }
    };


    protected void startLocationUpdate() {
        LocationServices
                .FusedLocationApi
                .requestLocationUpdates(
                        googleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdate() {
        LocationServices
                .FusedLocationApi
                .removeLocationUpdates(googleApiClient, this);
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(SMALLEST_DISPLACEMENT);
    }



    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (googleApiClient.isConnected())
        startLocationUpdate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }

    protected void onPause() {
        super.onPause();
        if (googleApiClient.isConnected()) {
            startLocationUpdate();
            statusSesi = true;
        }
    }


    @Override
    public void onMapReady(GoogleMap mMap) {

        googleMap = mMap;
        /**
         * Setup Maps GUI
         * */
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.setTrafficEnabled(true);

        /**
         * Get Current Location
         */
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    // Inisialisasi google map, implementasi class
    @Override
    public void onConnected(Bundle bundle) {

        if (statusSesi){
            startLocationUpdate();
        } else {
            stopLocationUpdate();
        }

        if (mLocation == null) {
            // get last location device
            mLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (googleMap != null) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLocation.getLatitude(), mLocation.getLongitude()), 13));
                lat = mLocation.getLatitude();
                lng = mLocation.getLongitude();

                Toast.makeText(this, "Lokasi kamu saat ini ", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
        if (endMarker!=null){
            endMarker.remove();
        }

        TextView title = (TextView) findViewById(R.id.title);
        lat = mLocation.getLatitude();
        lng = mLocation.getLongitude();
        loc = new LatLng(lat,lng);
        newpoint = loc.toString();
        session_type = btnExcercise.getText().toString();

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.CYAN);
        polylineOptions.width(3);
        point.add(loc);
        polylineOptions.addAll(point);
        googleMap.addPolyline(polylineOptions);
        startLoc = point.get(0);
        if (point.size() == 1){
            startMarker = googleMap.addMarker(new MarkerOptions().
                    position(startLoc).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        } else {
            endMarker = googleMap.addMarker(new MarkerOptions().
                    position(loc).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }

        if (point.size()>=2){
            jarakS =0;
            LatLng start = point.get(point.size()-2);
            LatLng end = point.get(point.size()-1);

            jarakS = getDistance(start, end);

            jarak += jarakS;
        }

        String jarakString = jarak + " m";

        if (jarak > 1000.0f){
            jarak = jarak/1000.0f;
            jarakString = jarak + " KM";
        }

        endTime = System.currentTimeMillis();
        if (endTime > startTime){
            time = endTime - startTime;
        }

// array_loc is debugger to monitoring all activities.
// until the development is done,
// we can see all activities happend right there.
        array_loc.setText("Session: " + session_type +
                "\n point: "+point.size()+
                "\nLocation " + point);
        title.setText("Lat = " + loc.latitude +
                " Long = " +loc.longitude);



        textDistance.setText(""+jarakString);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private float getDistance(LatLng startPoint, LatLng endPoint) {
        Location lo = new  Location("one");
        lo.setLatitude(startPoint.latitude);
        lo.setLongitude(startPoint.longitude);

        Location lo2 = new Location("two");
        lo2.setLatitude(endPoint.latitude);
        lo2.setLongitude(endPoint.longitude);

        float distance = lo.distanceTo(lo2);

        return distance;
    }


}