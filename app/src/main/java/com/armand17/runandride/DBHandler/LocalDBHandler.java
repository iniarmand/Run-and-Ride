package com.armand17.runandride.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;

import java.util.List;

/**
 * Created by armand17 on 06/10/15.
 */
public class LocalDBHandler extends SQLiteOpenHelper {

    //  Version number of the database
    private static int VERSION = 1;
    //     Database name

    private static String DBNAME = "DatabaseTracking";

    // the table name
    public static final String DATABASE_TABLE = "run_and_ride";

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

    private static String CreateTableRunAndRide = "create table " + DATABASE_TABLE + " ( " +
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
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableRunAndRide);
    }

    // Menambahkan data ke dalam tabel
    public long insert(ContentValues contentValues) {
        long rowID;
        rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    // Delete All data atau Reset Data
    public int reset() {
        int reset;
        reset = mDB.delete(DATABASE_TABLE, null, null);
        return reset;
    }

    // Membaca semua data yang ada di dalam tabel
    public Cursor getAllData() {
        return mDB.query(DATABASE_TABLE, new String[]{FIELD_ROW_ID,
                FIELD_SESSION_TYPE,
                FIELD_SESSION_NAME,
                FIELD_SESSION_DESC,
                FIELD_SESSION_TIME,
                FIELD_ELAPSED_TIME,
                FIELD_ARRAYPOINT,
                FIELD_DISTANCE,
                FIELD_SPEED,
                FIELD_HEART_RATE,
                FIELD_CALLORIES}, null, null, null, null, null, null);
    }

   // public List<String>

    // Membaca data yang di pilih



    // Menghapus data terpilih



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
