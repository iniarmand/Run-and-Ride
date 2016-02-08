package com.armand17.runandride.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;

import com.armand17.runandride.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by armand17 on 06/10/15.
 */
public class LocalDBHandler extends SQLiteOpenHelper {

    //  Version number of the database
    private static int VERSION = 1;

    //  Database name
    private static String DATABASE_NAME = "dbtracking";

    // the table name
    private static final String DATABASE_TABLE = "runandride";

    // Columns names
    public static final String FIELD_ROW_ID = "id";
    public static final String FIELD_SESSION_TYPE = "session_type";
    public static final String FIELD_SESSION_NAME = "name";
    public static final String FIELD_SESSION_DESC = "session_desc";
    public static final String FIELD_SESSION_TIME = "session_time";
    public static final String FIELD_ELAPSED_TIME = "elapsed_time";
    public static final String FIELD_ARRAYPOINT = "array_point";
    public static final String FIELD_DISTANCE = "distance";
    public static final String FIELD_SPEED = "speed";
    public static final String FIELD_HEART_RATE = "heart_rate";
    public static final String FIELD_CALLORIES = "call";

    private static String CreateTableRunAndRide = "create table " +
            DATABASE_TABLE + " ( " +
            FIELD_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_SESSION_TYPE + " TEXT," +
            FIELD_SESSION_NAME + " TEXT," +
            FIELD_SESSION_DESC + " TEXT," +
            FIELD_SESSION_TIME + " TEXT," +
            FIELD_ELAPSED_TIME + " REAL," +
            FIELD_ARRAYPOINT +" TEXT,"+
            FIELD_DISTANCE + " REAL," +
            FIELD_SPEED + " REAL," +
            FIELD_HEART_RATE + " INTEGER," +
            FIELD_CALLORIES + " REAL" +
            " ) ";

    /**
     * An instance variable for SQLiteDatabase
     */
    private SQLiteDatabase mDB;

    public LocalDBHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableRunAndRide);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_NAME);
        onCreate(db);
    }

    /**
     * Melakukan Operasi CRUD yaitu
     * CREATE
     * READ
     * UPDATE
     * DELETE
     */


    // CREATE Menambahkan data ke dalam tabel
    public long insert(ContentValues contentValues) {
        long rowID;
        rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    //READ Membaca semua data

    // Membaca semua data yang ada di dalam tabel
    public Cursor getAllSession() {
//        List<Session> sessionList = new ArrayList<Session>();

//        SQLiteDatabase db = this.getReadableDatabase();
        String [] selectQuery = {FIELD_ROW_ID,
                FIELD_SESSION_TYPE,
                FIELD_SESSION_NAME,
                FIELD_SESSION_DESC,
                FIELD_SESSION_TIME,
                FIELD_ELAPSED_TIME,
                FIELD_ARRAYPOINT,
                FIELD_DISTANCE,
                FIELD_SPEED,
                FIELD_HEART_RATE,
                FIELD_CALLORIES };
//        String selectQuery = "SELECT * FROM " + DATABASE_TABLE+ " ORDER BY id DESC";
        return mDB.query(DATABASE_TABLE,selectQuery,null,null,null,null,null);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
//
//        //Looping semua data session dan memasukkan ke dalam sessionList
//        if (cursor.moveToFirst()){
//            do {
//                Session session = new Session();
//                session.set_id(get_id(cursor));
//                session.set_sessionType(getFieldSessionType(cursor));
//                session.set_sessionName(getFieldSessionName(cursor));
//
//                try {
//                    session.set_sessionTime(dateFormat.parse(getFieldSessionTime(cursor)));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                session.set_distance(Float.parseFloat(getFieldDistance(cursor)));
//                session.set_callorie(Float.parseFloat(getFieldCallories(cursor)));
//
//
//                // Menambahkan ke array sessionList
//                sessionList.add(session);
//            } while (cursor.moveToNext());
//        }
////
////        cursor.close();
////        mDB.close();
//        return sessionList;
    }

    //READ Membaca data di pilih
//    public Session getSessionDetail(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
//
//        Cursor cursor = db.query(DATABASE_TABLE, new String[]{FIELD_ROW_ID,
//                FIELD_SESSION_TYPE,
//                FIELD_SESSION_NAME,
//                FIELD_SESSION_DESC,
//                FIELD_SESSION_TIME,
//                FIELD_ELAPSED_TIME,
//                FIELD_ARRAYPOINT,
//                FIELD_DISTANCE,
//                FIELD_SPEED,
//                FIELD_HEART_RATE,
//                FIELD_CALLORIES}, FIELD_ROW_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
////        Session session = new Session(get_id(cursor),
////                getFieldSessionType(cursor),
////                getFieldSessionName(cursor),
////                getFieldSessionDesc(cursor),
////                getFieldSessionTime(cursor),
////                getFieldElapsedTime(cursor),
////                getFieldArraypoint(cursor),
////                getFieldDistance(cursor),
////                getFieldSpeed(cursor),
////                getFieldHeartRate(cursor),
////                getFieldCallories(cursor));
//
//    }
    //UPDATE Tidak ada fitur update data


    // Delete All data atau Reset Data
    public int reset() {
        int reset;
        reset = mDB.delete(DATABASE_TABLE, null, null);
        return reset;
    }


    // Menghapus data terpilih


    /** Mengambil field data dari semua data yang telah di panggil
     *
     */
    public int get_id(Cursor cursor){
        return cursor.getInt(0);
    }

    public String getFieldSessionType(Cursor cursor){
        return(cursor.getString(1));
    }

    public String getFieldSessionName(Cursor cursor){
        return(cursor.getString(2));
    }

    public String getFieldSessionDesc(Cursor cursor){
        return(cursor.getString(3));
    }

    public String getFieldSessionTime(Cursor cursor){
        return(cursor.getString(4));
    }

    public String getFieldElapsedTime(Cursor cursor){
        return(cursor.getString(5));
    }

    public String getFieldArraypoint(Cursor cursor){
        return(cursor.getString(6));
    }

    public String getFieldDistance(Cursor cursor){
        return(cursor.getString(7));
    }

    public String getFieldSpeed(Cursor cursor){
        return(cursor.getString(8));
    }

    public String getFieldHeartRate(Cursor cursor){
        return(cursor.getString(9));
    }

    public String getFieldCallories(Cursor cursor){
        return(cursor.getString(10));
    }


}
