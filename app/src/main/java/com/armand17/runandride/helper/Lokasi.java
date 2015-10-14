package com.armand17.runandride.helper;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by armand17 on 11/10/15.
 */
public class Lokasi {

    private LatLng latLng;
    private double lat;
    private double lng;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
