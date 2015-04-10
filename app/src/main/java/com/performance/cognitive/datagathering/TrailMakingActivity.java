package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import canvas.DrawingPanel;
import datastructure.SpeedNumberTask;
import scheduler.Scheduler;


public class TrailMakingActivity extends Activity {
    DrawingPanel drawView;
    Context context;
    Timer mTimer;
    Handler mHandler;
    static int score;
    SpeedNumberTask speed;
    static float secondElapsed;
    public static boolean timeToReset = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        secondElapsed = 0;
        score = 0;
        speed = new SpeedNumberTask();
        Scheduler.getInstance().activityStart(speed);
        drawView = new DrawingPanel(context);
        setContentView(drawView);
        //drawView.requestFocus();
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mHandler = new Handler();
        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 0, 500);
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    setSecondElapsed(++secondElapsed);
                    drawView.invalidate();
                    Log.i("Seconds", "  " + getSecondElapsed());
                    if (timeToReset) {
                        drawView = new DrawingPanel(context);
                        setContentView(drawView);
                        score++;
                        timeToReset = false;
                    }
                    if (getSecondElapsed() / 2 > 30) {
                        Log.i("Seconds", "  " + getSecondElapsed());
                        mTimer.cancel();
                        speed.setScore(score);
                        Scheduler.getInstance().activityStop(speed, true);
//
                        TrailMakingActivity.this.finish();
                    }
                }
            });
        }
    }

    public static float getSecondElapsed() {
        return secondElapsed;
    }

    public static void setSecondElapsed(float secondElapsed) {
        TrailMakingActivity.secondElapsed = secondElapsed;
    }
}
