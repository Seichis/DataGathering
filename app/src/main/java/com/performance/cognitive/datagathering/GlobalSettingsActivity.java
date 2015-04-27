package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;


public class GlobalSettingsActivity extends Activity {
    EditText inputDO, inputDS, inputCoordRad, inputCoordDist, inputLT;
    Button saveSettings, startSession;
    SharedPreferences pref;
    Timer mTimer;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);
        inputDO = (EditText) findViewById(R.id.input_time_digit_order);
        inputDS = (EditText) findViewById(R.id.input_time_digit_span);
        inputCoordDist = (EditText) findViewById(R.id.input_distance_coordination);
        inputCoordRad = (EditText) findViewById(R.id.input_radius_coordination);
        inputLT = (EditText) findViewById(R.id.input_time_long_term);
        pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

// We need an editor object to make changes
        final SharedPreferences.Editor edit = pref.edit();

        startSession = (Button) findViewById(R.id.startSessionButton);
        saveSettings = (Button) findViewById(R.id.save_settings_button);

        inputDO.setHint(Integer.toString(pref.getInt("do", 0)));
        inputDS.setHint(Integer.toString(pref.getInt("ds", 0)));
        inputCoordDist.setHint(Float.toString(pref.getFloat("coordDist", 0f)));
        inputCoordRad.setHint(Integer.toString(pref.getInt("coordRad", 0)));
        inputLT.setHint(Integer.toString(pref.getInt("longTerm", 0)));


        startSession.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                } else {
                    mTimer = new Timer();
                }

                mHandler = new Handler();
                mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 300000, 3600000);

            }
        });

        saveSettings.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
// Set/Store data
                edit.putInt("do", Integer.parseInt(inputDO.getText().toString()));
                edit.putInt("ds", Integer.parseInt(inputDS.getText().toString()));
                edit.putInt("coordRad", Integer.parseInt(inputCoordRad.getText().toString()));
                edit.putFloat("coordDist", Float.parseFloat(inputCoordDist.getText().toString()));
                edit.putInt("longTerm", Integer.parseInt(inputLT.getText().toString()));

// Commit the changes
                edit.commit();
            }
        });

    }


    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }
}
