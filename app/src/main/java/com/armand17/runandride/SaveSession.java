package com.armand17.runandride;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.armand17.runandride.helper.TimeFormatter;

public class SaveSession extends FragmentActivity {

    TextView textNamaSesi,textJarak,textTime,textSpeed,textHeartRate,textKalori;
    EditText Judul,Ket;
    Button BtnShare, BtnSave, BtnCancel;
    int speed;
    String namaSesi;
    float dataJarak, heartRate, callorie;
    long dataTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_session);

        BtnSave = (Button) findViewById(R.id.btnSave);
        BtnShare = (Button) findViewById(R.id.btnShare);
        BtnCancel = (Button) findViewById(R.id.btnCancel);


        textNamaSesi = (TextView) findViewById(R.id.namaSesi);
        textJarak = (TextView) findViewById(R.id.saveJarak);
        textTime = (TextView) findViewById(R.id.saveTime);
        textHeartRate = (TextView) findViewById(R.id.saveHeart);
        textSpeed = (TextView) findViewById(R.id.saveSpeed);
        textKalori = (TextView) findViewById(R.id.saveCalories);

        Judul = (EditText) findViewById(R.id.saveJudul);
        Ket = (EditText) findViewById(R.id.saveKet);

        Intent data = getIntent();
        namaSesi = data.getStringExtra("session");
        dataJarak = data.getFloatExtra("jarak", 0000);
        dataTime = data.getLongExtra("time", 0000);

        String formattedTime;
        formattedTime = TimeFormatter.timeFormatter(dataTime);

        heartRate = 93;
        callorie = 340;
        speed = 100;

        textNamaSesi.setText(namaSesi);
        textJarak.setText(""+dataJarak);
        textTime.setText(formattedTime);
        textSpeed.setText(""+speed);
        textHeartRate.setText(""+heartRate);

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SaveSession.this, "You Click Save Button", Toast.LENGTH_SHORT).show();

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
}
