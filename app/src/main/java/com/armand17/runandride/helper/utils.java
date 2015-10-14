package com.armand17.runandride.helper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by armand17 on 10/10/15.
 */
public class utils {
    /**
     * Created by armand17 on 10/10/15.

    public static List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList < LatLng >;
        int index = 0, lngth = encoded.length();
        int lat = 0, lng = 0;
        while (index < lngth){
            int b, shift = 0, result = 0;
            b = encoded.charAt(index++) - 63;
            result |= (b&0x1f) << shift;
            shift += 5;
        } while (b)

    }



    public static getCurrentTimestamp() {
        Long timeStamp = System.currentTimeMillis() / 1000;
        return timeStamp.toString();
    }
     */
}
