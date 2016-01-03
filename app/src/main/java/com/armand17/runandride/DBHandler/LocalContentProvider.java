package com.armand17.runandride.DBHandler;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import java.sql.SQLException;

/**
 * Created by armand17 on 26/12/15.
 */
public class LocalContentProvider extends ContentProvider{

    // Provider Name
    public static final String PROVIDER_NAME = "com.armand17.runandride.DBHandler.LocalDBHandler.run_and_ride";

    // URI Parse
    public static final Uri CONTENT_URI = Uri.parse("content://"+PROVIDER_NAME+"/run_and_ride" );

    // Constant to identify request operation
    private static final int LOCATIONS = 1;

    private static final UriMatcher uriMatcher ;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "run_and_ride", LOCATIONS);
    }

    LocalDBHandler mLocalDBHandler;

    @Override
    public boolean onCreate() {
        mLocalDBHandler = new LocalDBHandler(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri)==LOCATIONS){
            return mLocalDBHandler.getAllData();
        }
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = mLocalDBHandler.insert(values);
        Uri _uri = null;
        if (rowID>0){
            _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        } else {
            try {
                throw new SQLException("Gagal Insert Data : " + uri);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int reset = 0;
        reset = mLocalDBHandler.reset();
        return reset;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
