package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dataoperations.DataOperations;
import settings.SettingsObj;


public class GlobalSettingsActivity extends ActionBarActivity {
    EditText inputDO,inputDS,inputCoordRad,inputCoordDist,inputLT;
    Button saveSettings;
    SettingsObj mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        inputDO=(EditText)findViewById(R.id.input_time_digit_order);
        inputDS=(EditText)findViewById(R.id.input_time_digit_span);
        inputCoordDist=(EditText)findViewById(R.id.input_distance_coordination);
        inputCoordRad=(EditText)findViewById(R.id.input_radius_coordination);
        inputLT=(EditText)findViewById(R.id.input_time_long_term);
        saveSettings=(Button)findViewById(R.id.save_settings_button);
        saveSettings.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DataOperations.getInstance().clearSettingsFile();
                mSettings=new SettingsObj();
                mSettings.setCoordDistanceFromCenter(Float.parseFloat(inputCoordDist.getText().toString()));
                mSettings.setCoordRadiusOfCircle(Integer.parseInt(inputCoordRad.getText().toString()));
                mSettings.setDigitOrderShowNumberTimeSpan(Integer.parseInt(inputDO.getText().toString()));
                mSettings.setDigitSpanShowNumberTimeSpan(Integer.parseInt(inputDS.getText().toString()));
                mSettings.setLongTermShowWordsTimeSpan(Integer.parseInt(inputLT.getText().toString()));
                String temp = DataOperations.getInstance().settingsToJSON(mSettings);
                DataOperations.getInstance().writeToSettingsFile(temp);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_global_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
