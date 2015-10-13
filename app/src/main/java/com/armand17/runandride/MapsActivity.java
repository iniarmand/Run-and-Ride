package com.armand17.runandride;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by armand17 on 09/10/15.
 */
public class MapsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnMyLocationChangeListener{

    GoogleMap googleMap;
    ArrayList<LatLng> points;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());



        points = new ArrayList<>();


        googleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(context);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(true);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);

        String provider = locationManager.getBestProvider(criteria, true);

        Location location = locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);


        locationManager.requestLocationUpdates(provider,100,1, locationListener);

    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }
    };

    private void updateWithNewLocation(Location location) {
        String latlongstring ;
        TextView yourLoc = (TextView)findViewById(R.id.title);
        TextView arrayLoc = (TextView)findViewById(R.id.array_loc);

        String addressString = "Address not found";
        LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.BLUE);
        polylineOptions.width(3);
        points.add(loc);
        polylineOptions.addAll(points);
        googleMap.addPolyline(polylineOptions);
        arrayLoc.setText(points.toString());
        if (location != null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latlongstring = "Lat = "+lat+"\nLong = "+lng;

            Geocoder gc = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = gc.getFromLocation(lat,lng,1);
                StringBuilder sb = new StringBuilder();
                if (addresses.size()>0){
                    Address address = addresses.get(0);
                    for (int i = 1; i < address.getMaxAddressLineIndex(); i++){
                        sb.append(address.getLocality()).append(", ");
                        sb.append(address.getCountryName());
                    }
                    addressString = sb.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            latlongstring = "Waiting for location";
        }

        yourLoc.setText(addressString);
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

    @Override
    public void onMyLocationChange(Location location) {

    }
}
