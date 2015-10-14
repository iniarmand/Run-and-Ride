package com.armand17.runandride.helper;

/**
 * Created by armand17 on 14/10/15.
 */
public class GetTime {
    private long time;
    private long startTime;
    private long stopTime;

    public long getTime() {
        time = System.currentTimeMillis();
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
