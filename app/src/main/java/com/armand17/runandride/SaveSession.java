package com.armand17.runandride;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.armand17.runandride.DBHandler.LocalContentProvider;
import com.armand17.runandride.DBHandler.LocalDBHandler;
import com.armand17.runandride.helper.TimeFormatter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SaveSession extends FragmentActivity {

    TextView textSessionType,textTime, textJarak,textTimeElapsed,
            textSpeed,textHeartRate,textKalori, textArrayPoint;
    EditText judulSesi ,Ket;
    Button BtnShare, BtnSave, BtnCancel;
    int speed;
    String sessionType, stringPoint, JudulSesi, Description;
    float dataJarak, heartRate, callorie;
    long dataTimeElapsed;
    Date dataTime;
    ArrayList<LatLng> arrayPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_session);

        BtnSave = (Button) findViewById(R.id.btnSave);
        BtnShare = (Button) findViewById(R.id.btnShare);
        BtnCancel = (Button) findViewById(R.id.btnCancel);


        textSessionType = (TextView) findViewById(R.id.namaSesi);
        textTime = (TextView) findViewById(R.id.saveTime);
        textJarak = (TextView) findViewById(R.id.saveJarak);
        textTimeElapsed = (TextView) findViewById(R.id.saveTimeElapsed);
        textHeartRate = (TextView) findViewById(R.id.saveHeart);
        textSpeed = (TextView) findViewById(R.id.saveSpeed);
        textKalori = (TextView) findViewById(R.id.saveCalories);
        textArrayPoint = (TextView)findViewById(R.id.arrayPoint);

        judulSesi = (EditText) findViewById(R.id.saveJudul);
        Ket = (EditText) findViewById(R.id.saveKet);

//        Getting Array point location from prev activity
//        Bundle rcPoint = getIntent().getExtras();
//        arrayPoint = rcPoint.getParcelableArrayList("arrayPoint");

//      Getting normal data from prev activity
        // we have make change on this comment
        Intent data = getIntent();
        sessionType = data.getStringExtra("session");
        dataJarak = data.getFloatExtra("jarak", 0);
        dataTimeElapsed = data.getLongExtra("timeElapsed", 0);
        arrayPoint = (ArrayList<LatLng>) data.getSerializableExtra("arrayPoint");

        //Set Array of Location to string
        stringPoint = arrayPoint.toString();

        //Get Date and time
        Calendar calendar = Calendar.getInstance();
        int mins = calendar.get(Calendar.MINUTE);
        int hr = calendar.get(Calendar.HOUR);

        dataTime = calendar.getTime();

        // Formatting elapsed time from long value to Hour:Minutes:Second.
        String formattedTime;
        formattedTime = TimeFormatter.timeFormatter(dataTimeElapsed);

        heartRate = 93;
        callorie = 340;
        speed = 100;

        textSessionType.setText(sessionType);
        textTime.setText(""+ dataTime );
        textJarak.setText(""+dataJarak);
        textTimeElapsed.setText(formattedTime);
        textSpeed.setText(""+speed);
        textHeartRate.setText(""+heartRate);
        textArrayPoint.setText(""+stringPoint);

        JudulSesi = String.valueOf(judulSesi.getText());
        Description = String.valueOf(Ket.getText());

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrepareToSave();

//                Toast.makeText(SaveSession.this, "You Click Save Button", Toast.LENGTH_SHORT).show();

            }
        });

        BtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaveSession.this, "You Click Share Button",Toast.LENGTH_SHORT).show();

            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaveSession.this, "You Click Cancel Button",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void PrepareToSave() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(LocalDBHandler.FIELD_SESSION_TYPE, sessionType);
        contentValues.put(LocalDBHandler.FIELD_SESSION_NAME, JudulSesi);
        contentValues.put(LocalDBHandler.FIELD_SESSION_DESC, Description);
        contentValues.put(LocalDBHandler.FIELD_SESSION_TIME,dataTime.toString());
        contentValues.put(LocalDBHandler.FIELD_ELAPSED_TIME, dataTimeElapsed );
        contentValues.put(LocalDBHandler.FIELD_ARRAYPOINT,stringPoint);
        contentValues.put(LocalDBHandler.FIELD_DISTANCE, dataJarak);
        contentValues.put(LocalDBHandler.FIELD_SPEED, speed);
        contentValues.put(LocalDBHandler.FIELD_HEART_RATE, heartRate);
        contentValues.put(LocalDBHandler.FIELD_CALLORIES, callorie);

        SaveToDatabase savetoDB = new SaveToDatabase();

        savetoDB.execute(contentValues);

        Intent saved = new Intent(this,SavedSessionList.class);
        saved.putExtra("toast", "Data tersimpan");

        startActivity(saved);
    }

    private class SaveToDatabase extends AsyncTask<ContentValues,Void,Void>{
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            getContentResolver().insert(LocalContentProvider.CONTENT_URI,contentValues[0]);
            return null;
        }
    }
}
