package com.armand17.runandride.helper;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by armand17 on 13/10/15.
 */
public class GetDistance {

    private Location startPoint;
    private Location endPoint;
    float distance;

    public float GetDistance(Location startPoint, Location endPoint) {

        this.startPoint = startPoint;
        this.endPoint = endPoint;
        distance = startPoint.distanceTo(endPoint);
        return distance;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }
}
