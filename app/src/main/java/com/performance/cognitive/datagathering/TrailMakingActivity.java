package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import canvas.Dots;
import canvas.DrawingPanel;
import canvas.DrawingPanelOneShot;
import datastructure.SpeedTask;
import scheduler.Scheduler;


public class TrailMakingActivity extends Activity {
    DrawingPanel drawView;
    Context context;
    Timer mTimer;
    Handler mHandler;
    static int score=0;
    SpeedTask speed;



    static float secondElapsed=0;
    public static boolean timeToReset = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context=this;
        speed=new SpeedTask();
        Scheduler.getInstance().activityStart(speed);
        drawView = new DrawingPanel(context);
        setContentView(drawView);
        drawView.requestFocus();

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mHandler = new Handler();
        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 0, 500);


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
                    if (timeToReset){
                        drawView = new DrawingPanel(context);
                        setContentView(drawView);
                        score++;
                        timeToReset=false;
                    }
                    if(getSecondElapsed()/2>30){
                        Log.i("Seconds", "  " + getSecondElapsed());

                        speed.setScore(score);
                        Scheduler.getInstance().activityStop(speed, true);
                        score=0;
                        mTimer.cancel();
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
