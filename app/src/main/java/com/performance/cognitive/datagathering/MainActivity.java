package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import datastructure.SelectiveAttentionTask;


public class MainActivity extends ActionBarActivity {
    Button attention1Button, attention2Button, coordinationButton, fluencyButton, longTermMemoryButton, tapSpeedButton, reactionTimeButton, selectiveAttentionButton, speedButton, statsButton;

    boolean pressedTest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        attention1Button = (Button) findViewById(R.id.attention1_button);
        attention2Button = (Button) findViewById(R.id.attention2_button);
        fluencyButton = (Button) findViewById(R.id.fluency_button);
        speedButton = (Button) findViewById(R.id.speed_button);
        coordinationButton = (Button) findViewById(R.id.coordination_button);
        statsButton = (Button) findViewById(R.id.stats_button);
        longTermMemoryButton = (Button) findViewById(R.id.longtermmemory_button);
        tapSpeedButton = (Button) findViewById(R.id.tapspeed_button);
        reactionTimeButton = (Button) findViewById(R.id.reactiontime_button);
        selectiveAttentionButton = (Button) findViewById(R.id.selectiveattention_button);

         //    startActivity(new Intent(MainActivity.this,SelectiveActivity.class));

        attention1Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DigitSpan.class));

            }
        });
        attention2Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DigitOrder.class));

            }
        });
        fluencyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ConnectDotsOneShotActivity.class));


            }
        });
        speedButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TrailMakingActivity.class));


            }
        });
        coordinationButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, BubbleActivity.class));


            }
        });
        statsButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OverviewActivity.class));

            }
        });
        longTermMemoryButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Words.class));
            }
        });
        tapSpeedButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TapActivity.class));
            }
        });
        reactionTimeButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorTapActivity.class));
            }
        });
        selectiveAttentionButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectiveActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
