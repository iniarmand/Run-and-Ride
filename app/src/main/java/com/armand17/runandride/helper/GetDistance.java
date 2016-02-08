package com.armand17.runandride.helper;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by armand17 on 13/10/15.
 */
public class GetDistance {

    private Location startPoint;
    private Location endPoint;
    private static float distance;

    public static float getDistance(LatLng startPoint, LatLng endPoint) {
        Location lo = new  Location("one");
        lo.setLatitude(startPoint.latitude);
        lo.setLongitude(startPoint.longitude);

        Location lo2 = new Location("two");
        lo2.setLatitude(endPoint.latitude);
        lo2.setLongitude(endPoint.longitude);

        distance = lo.distanceTo(lo2);

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
