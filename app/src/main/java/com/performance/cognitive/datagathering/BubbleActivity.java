package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import canvas.Bubble;
import canvas.Orientation;

public class BubbleActivity extends Activity implements Orientation.Listener, SensorEventListener {
    private SensorManager manager;
    private Bubble bubbleView;
    private Sensor accel;
    Orientation mOrientation;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void onOrientationChanged(float pitch, float roll) {
        bubbleView.move((2)*roll, (float) (1.5* pitch));
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