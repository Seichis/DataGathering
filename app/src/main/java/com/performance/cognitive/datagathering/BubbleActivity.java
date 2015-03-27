package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import canvas.Bubble;
import canvas.Orientation;
import datastructure.CoordinationTask;
import scheduler.Scheduler;

public class BubbleActivity extends Activity implements Orientation.Listener, SensorEventListener {
    private SensorManager manager;
    TextView info;
    Button playButton;
    private Bubble bubbleView;
    private Sensor accel;
    Orientation mOrientation;
    CoordinationTask coordination;
    Timer mTimer;
    Handler mHandler;
    public static int sec;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        playButton = (Button) findViewById(R.id.playbutton);
        info = (TextView) findViewById(R.id.info);
        info.setText("Control the position of the ball in the center of the screen");
        playButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                playButton.setVisibility(View.INVISIBLE);
                info.setVisibility(View.INVISIBLE);
            }
        });
        sec = 0;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        bubbleView = new Bubble(this);
        setContentView(bubbleView);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
        mOrientation = new Orientation(
                (SensorManager) getSystemService(Activity.SENSOR_SERVICE),
                getWindow().getWindowManager());
        coordination = new CoordinationTask();
        Scheduler.getInstance().activityStart(coordination);
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mHandler = new Handler();
        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 0, 1000);


    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    sec++;
                    bubbleView.invalidate();
                    if (sec > 30) {
                        coordination.setScore((int) bubbleView.getScore());
                        Scheduler.getInstance().activityStop(coordination, true);
                        BubbleActivity.this.finish();
                        mTimer.cancel();
                    }

                }
            });
        }
    }


    @Override
    public void onOrientationChanged(float pitch, float roll) {
        bubbleView.move((2) * roll, (float) (2.5 * roll));
        bubbleView.invalidate();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        bubbleView.move(-event.values[0], event.values[1]);
        bubbleView.invalidate();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mOrientation.startListening(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
        mOrientation.stopListening();
    }
}