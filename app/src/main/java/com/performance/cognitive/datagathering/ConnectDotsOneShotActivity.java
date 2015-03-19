package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import canvas.DrawingPanelOneShot;
import datastructure.FluencyTask;
import scheduler.Scheduler;


public class ConnectDotsOneShotActivity extends Activity {
    Timer mTimerProgress,mTimerGame;
    Handler mHandlerProgress,mHandlerGame;
    DrawingPanelOneShot drawView;
    public static float second=0;
    public static boolean timeToReset = false;
    Context context;
    FluencyTask fluency;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context=this;
        fluency = new FluencyTask();
        Scheduler.getInstance().activityStart(fluency);
        drawView = new DrawingPanelOneShot(context);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(drawView);
        drawView.requestFocus();
        if (mTimerProgress != null) {
            mTimerProgress.cancel();
        } else {
            mTimerProgress = new Timer();
        }
        mTimerProgress.scheduleAtFixedRate(new ProgressBarTimerTask(), 0, 1000);
        mHandlerGame = new Handler();
        if (mTimerGame != null) {
            mTimerGame.cancel();
        } else {
            mTimerGame = new Timer();
        }

        mTimerGame.scheduleAtFixedRate(new ActionsTimerTask(), 0, 250);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    class ProgressBarTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandlerGame.post(new Runnable() {
                @Override
                public void run() {

                    if(second>30){
                        mTimerProgress.cancel();
                        fluency.setScore(DrawingPanelOneShot.score);
                        Scheduler.getInstance().activityStop(fluency,true);
                        ConnectDotsOneShotActivity.this.finish();
                    }
                   second++;



                }
            });
        }
    }
    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandlerGame.post(new Runnable() {

                @Override
                public void run() {
                    if (timeToReset){
                        setContentView(new DrawingPanelOneShot(context));
                        timeToReset=false;
                    }
                }
            });
        }
    }

}