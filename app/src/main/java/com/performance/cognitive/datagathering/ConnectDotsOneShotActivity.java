package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import adapters.ImageAdapter;
import canvas.DrawingPanelOneShot;
import datastructure.FluencyTask;
import scheduler.Scheduler;


public class ConnectDotsOneShotActivity extends Activity {
//    Timer mTimerGame;
    Timer mTimerProgress;
    Handler mHandlerGame;
    DrawingPanelOneShot drawView,drawReset;
    public static float second=0;
    public static boolean timeToReset = false;
    int firstTimercount = 0;
    FluencyTask fluency;
    static GridView mGridView;
    static ImageAdapter mImageAdapter;
    public static int positionOfAnswerArray;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        positionOfAnswerArray=0;
        setContentView(R.layout.activity_connect_dots_one_shot);
        fluency = new FluencyTask();
        Scheduler.getInstance().activityStart(fluency);
        drawView =(DrawingPanelOneShot)findViewById(R.id.drawPanel);

        drawView.requestFocus();
        if (mTimerProgress != null) {
            mTimerProgress.cancel();
        } else {
            mTimerProgress = new Timer();
        }
        mTimerProgress.scheduleAtFixedRate(new ProgressBarTimerTask(), 0, 1000);
        mHandlerGame = new Handler();
        mGridView=(GridView)findViewById(R.id.answerImages);
        mImageAdapter = new ImageAdapter(this);
        mGridView.setAdapter(mImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class ProgressBarTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandlerGame.post(new Runnable() {
                @Override
                public void run() {
                    firstTimercount++;
                    Log.i("Timers","  "+ firstTimercount);
                    if(second>30){
                        mTimerProgress.cancel();
                        fluency.setScore(DrawingPanelOneShot.score);
                        Scheduler.getInstance().activityStop(fluency,true);
                        DrawingPanelOneShot.score=0;
                        second=0;
                        ConnectDotsOneShotActivity.this.finish();
                    }
                   second++;
                }
            });
        }
    }

     public static void setAdapterToShow(){
         mImageAdapter.notifyDataSetChanged();
     }
}