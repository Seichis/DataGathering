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


public class TrailMakingActivity extends Activity {
    DrawingPanelOneShot drawView;
    Context context;
    Timer mTimer;
    Handler mHandler;
    public static boolean timeToReset = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context=this;
        drawView = new DrawingPanelOneShot(context);
        setContentView(drawView);
        drawView.requestFocus();

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mHandler = new Handler();
        mTimer.scheduleAtFixedRate(new ActionsTimerTask(), 1000, 1000);

    }
    class ActionsTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

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
