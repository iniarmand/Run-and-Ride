package com.armand17.runandride;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by armand17 on 11/01/16.
 */
public class Session {

    // Private Variabel

    int _id;
    String _sessionType;
    String _sessionName;
    String _sessionDesc;
    Date _sessionTime;
    Float _elapsedTime;
    ArrayList<LatLng> _arrayPoint;
    Float _distance;
    int _speed;
    Float _heartRate;
    Float _callorie;

    // Constructor
    // Constructor Kosong

    public Session() {
    }

    // Constructor untuk List Item

    public Session(int _id, String _sessionType, String _sessionName, Date _sessionTime, Float _distance, Float _callorie) {
        this._id = _id;
        this._sessionType = _sessionType;
        this._sessionName = _sessionName;
        this._sessionTime = _sessionTime;
        this._distance = _distance;
        this._callorie = _callorie;
    }

    // Constructor Lengkap

    public Session(int _id, String _sessionType,
                   String _sessionName,
                   String _sessionDesc,
                   Date _sessionTime,
                   Float _elapsedTime,
                   ArrayList<LatLng> _arrayPoint,
                   Float _distance,
                   int _speed,
                   Float _heartRate,
                   Float _callorie) {
        this._id = _id;
        this._sessionType = _sessionType;
        this._sessionName = _sessionName;
        this._sessionDesc = _sessionDesc;
        this._sessionTime = _sessionTime;
        this._elapsedTime = _elapsedTime;
        this._arrayPoint = _arrayPoint;
        this._distance = _distance;
        this._speed = _speed;
        this._heartRate = _heartRate;
        this._callorie = _callorie;
    }


    /**
     * Getter and Setter Untuk Semua Variabel
     */

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_sessionType() {
        return _sessionType;
    }

    public void set_sessionType(String _sessionType) {
        this._sessionType = _sessionType;
    }

    public String get_sessionName() {
        return _sessionName;
    }

    public void set_sessionName(String _sessionName) {
        this._sessionName = _sessionName;
    }

    public String get_sessionDesc() {
        return _sessionDesc;
    }

    public void set_sessionDesc(String _sessionDesc) {
        this._sessionDesc = _sessionDesc;
    }

    public Date get_sessionTime() {
        return _sessionTime;
    }

    public void set_sessionTime(Date _sessionTime) {
        this._sessionTime = _sessionTime;
    }

    public Float get_elapsedTime() {
        return _elapsedTime;
    }

    public void set_elapsedTime(Float _elapsedTime) {
        this._elapsedTime = _elapsedTime;
    }

    public ArrayList<LatLng> get_arrayPoint() {
        return _arrayPoint;
    }

    public void set_arrayPoint(ArrayList<LatLng> _arrayPoint) {
        this._arrayPoint = _arrayPoint;
    }

    public Float get_distance() {
        return _distance;
    }

    public void set_distance(Float _distance) {
        this._distance = _distance;
    }

    public int get_speed() {
        return _speed;
    }

    public void set_speed(int _speed) {
        this._speed = _speed;
    }

    public Float get_heartRate() {
        return _heartRate;
    }

    public void set_heartRate(Float _heartRate) {
        this._heartRate = _heartRate;
    }

    public Float get_callorie() {
        return _callorie;
    }

    public void set_callorie(Float _callorie) {
        this._callorie = _callorie;
    }
}
