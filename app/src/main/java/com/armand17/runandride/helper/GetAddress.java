package com.armand17.runandride.helper;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import com.armand17.runandride.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

/**
 * Created by armand17 on 11/10/15.
 */
public class GetAddress {

    private static Location location;
    private static LatLng point;
    private static double latitude;
    private static double longitude;
    private static String address;


    public static String Update (double latitude, double longitude){

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        point = new LatLng(latitude,longitude);

//        Geocoder gc = new Geocoder(this, Locale.getDefault());


        return address;
    }

}
