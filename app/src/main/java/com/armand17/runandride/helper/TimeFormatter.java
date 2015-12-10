package com.armand17.runandride.helper;

import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

/**
 * Created by armand17 on 14/10/15.
 */
public class TimeFormatter {
    public static long secs,mins,hrs;
    public static String seconds,minutes,hours,milliseconds;
//    public String formattedTime ;



    public static String timeFormatter (long time){
        String formatedTime;
//        formatedTime = "";
        secs = (long) (time/1000);
        mins = (long) ((time/1000)/60);
        hrs = (long) (((time/1000)/60)/60);

        secs = secs%60;
        seconds = String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs <10 && secs > 0){
            seconds = "0"+seconds;
        }

//		 Convert the minutes to String and format the String

        mins = mins % 60;
        minutes=String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins <10 && mins > 0){
            minutes = "0"+minutes;
        }

    	/* Convert the hours to String and format the String */

        hours=String.valueOf(hrs);
        if(hrs == 0){
            hours = "00";
        }
        if(hrs <10 && hrs > 0){
            hours = "0"+hours;
        }

    	/* Although we are not using milliseconds on the timer in this example
    	 * I included the code in the event that you wanted to include it on your own
*/
        milliseconds = String.valueOf((long)time);
        if(milliseconds.length()==2){
            milliseconds = "0"+milliseconds;
        }
        if(milliseconds.length()<=1){
            milliseconds = "00";
        }
        milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);

        formatedTime = hours+":"+minutes+":"+seconds;//+"."+milliseconds);

        return formatedTime;
    }

}
