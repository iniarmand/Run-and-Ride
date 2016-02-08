package com.armand17.runandride;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.armand17.runandride.DBHandler.LocalContentProvider;
import com.armand17.runandride.DBHandler.LocalDBHandler;
import com.armand17.runandride.helper.CustomListAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SavedSessionList extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView recordList;
    private List<Session> sessionList;
    private CustomListAdapter adapter;
    
    
    LocalDBHandler dbHandler;
    private static final String[] PROJECTION = new String[]{"id",
            "session_type",
            "session_name",
            "session_desc",
            "session_time",
            "elapsed_time",
            "array_point",
            "distance",
            "speed",
            "heart_rate",
            "callories" };
    private TextView textTittle = null;
    private TextView textTime = null;
    private TextView textDistance = null;
    private TextView textCallorie = null;
    private TextView alldata;
    private ImageView iconSessionType;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_session_list);

        dbHandler = new LocalDBHandler(this);

        textTittle = (TextView)findViewById(R.id.tittleList);
        textTime = (TextView)findViewById(R.id.dateList);
        textCallorie = (TextView)findViewById(R.id.callorieList);
        iconSessionType = (ImageView)findViewById(R.id.iconList);
        alldata = (TextView)findViewById(R.id.alldata);

        recordList =(ListView)findViewById(R.id.recordList);
        adapter = new CustomListAdapter(this, sessionList);


//        cursor = dbHandler.getAllData();

//        Log.d("Reading", "Read All Session");
//        sessionList = dbHandler.getAllSession();

//        for (int i = 0; i<sessionList.size(); i++ ){
//
//            Session ss = sessionList.get(i);
//
//            int id = ss.get_id();
////            textTittle.setText(ss.);
//            textTittle.setText(""+ss.get_sessionName().toString());
//            textTime.setText(""+ss.get_sessionTime().toString());
//            textDistance.setText(""+ss.get_distance().toString());
//            textCallorie.setText(""+ss.get_callorie().toString());
//            if (ss.get_sessionType().equals("run")){
//                iconSessionType.setImageResource(R.mipmap.run);
//            } else if (ss.get_sessionType().equals("bike")){
//                iconSessionType.setImageResource(R.mipmap.bike);
//            }
//
//        }


//        for (Session ss : sessionList) {
//
//            alldata.setText(ss.toString());
////            textTittle.setText(ss.get_sessionName());
////            textTime.setText(ss.get_sessionTime().toString());
////            textDistance.setText(ss.get_distance().toString());
////            textCallorie.setText(ss.get_callorie().toString());
////            if (ss.get_sessionType().equals("run")){
////                iconSessionType.setImageResource(R.mipmap.run);
////            } else if (ss.get_sessionType().equals("bike")){
////                iconSessionType.setImageResource(R.mipmap.bike);
////            }
//
//        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri = LocalContentProvider.CONTENT_URI;

        return new CursorLoader(this,uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor session) {

        int sessionCount = 0;
        int id = 0;
        String sessionType = null;
        String sessionName = null;
        String sessionDesc = null;
        Date time = null;
        Long elapsedTime = null;
        String arrayPoint = null;
        Float jarak = null;
        Float speed = null;
        Float heartRate = null;
        Float callorie = null;
        Session ss = new Session();
        DateFormat dateFormat = new SimpleDateFormat("DD/MM/yyyy");

        sessionCount = session.getCount();

        session.moveToFirst();

        for (int i = 0; i < sessionCount; i++){
            ss.set_id(session.getInt(session.getColumnIndex(LocalDBHandler.FIELD_ROW_ID)));
            ss.set_sessionType(session.getString(session.getColumnIndex(LocalDBHandler.FIELD_SESSION_TYPE)));
            ss.set_sessionName(session.getString(session.getColumnIndex(LocalDBHandler.FIELD_SESSION_NAME)));
            ss.set_sessionDesc(session.getString(session.getColumnIndex(LocalDBHandler.FIELD_SESSION_DESC)));
            try {
                ss.set_sessionTime(dateFormat.parse(session.getString(session.getColumnIndex(LocalDBHandler.FIELD_SESSION_TIME))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        sessionList.add(ss);

        String data = sessionList.toString();

        alldata.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


//    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1){
//        Uri uri = LocalContentProvider.CONTENT_URI;
//
//        return new CursorLoader(this,uri,null,null,null,null);
//    }

//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        int sessionCount = 0;
//        int id = 0;
//        String sessionType = null;
//        String sessionName = null;
//        String sessionDesc = null;
//        String time = null;
//        Long elapsedTime = null;
//        String arrayPoint = null;
//        Float jarak = null;
//        Float speed = null;
//        Float heartRate = null;
//        Float callorie = null;
//
//        sessionCount = data.getCount();
//        data.moveToFirst();
//
//        for (int i =0; i < sessionCount; i++){
//            id = data.getInt(dbHandler.get_id(data));
//            sessionType = data.getString(dbHandler.get_id(data));
//            sessionName = data.getString(dbHandler.get_id(data));
//            sessionDesc = data.getString(dbHandler.get_id(data));
//            time = data.getString(dbHandler.get_id(data));
//            elapsedTime = data.getLong(dbHandler.get_id(data));
//            arrayPoint = data.getString(dbHandler.get_id(data));
//            jarak = data.getFloat(dbHandler.get_id(data));
//            speed = data.getFloat(dbHandler.get_id(data));
//            heartRate = data.getFloat(dbHandler.get_id(data));
//            callorie = data.getFloat(dbHandler.get_id(data));
//
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//
//    public void onLoaderFinished(Loader<Cursor> arg0, Cursor arg1){
//
//
//    }

//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//    }


//    static class ListHolder {
//        private TextView tittle = null;
//        private TextView time = null;
//        private TextView callorie = null;
//        private ImageView icon = null;
//        private View row = null;
//
//        ListHolder (View row){
//            this.row=row;
//
//            tittle = (TextView)row.findViewById(R.id.tittleList);
//            time = (TextView)row.findViewById(R.id.timeList);
//            callorie = (TextView)row.findViewById(R.id.callorieList);
//            icon = (ImageView)row.findViewById(R.id.iconList);
//        }
//
//        void PopulateFrom (Cursor c, LocalDBHandler dbHandler){
//            tittle.setText(dbHandler.getFieldSessionName(c));
//            time.setText(dbHandler.getFieldSessionTime(c));
//            callorie.setText(dbHandler.getFieldCallories(c));
//
//            if (dbHandler.getFieldSessionType(c).equals("run")){
//                icon.setImageResource(R.mipmap.run);
//            } else if (dbHandler.getFieldSessionType(c).equals("bike")){
//                icon.setImageResource(R.mipmap.bike);
//            }
//        }
//    }



}
