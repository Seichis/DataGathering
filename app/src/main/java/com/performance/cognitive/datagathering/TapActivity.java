package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import datastructure.SpeedTapTask;
import scheduler.Scheduler;


public class TapActivity extends ActionBarActivity {
    CountDownTimer count;
    TextView timer;
    ProgressBar progressBar;
    ImageView imageButton;
    TextView taps;
    TextView start;
    TextView info;
    Button playButton;
    SpeedTapTask speed;
    public static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        imageButton = (ImageView) findViewById(R.id.imageButton);
        imageButton.setVisibility(View.INVISIBLE);
        timer = (TextView) findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);
        taps = (TextView) findViewById(R.id.taps);
        taps.setVisibility(View.INVISIBLE);
        start = (TextView) findViewById(R.id.start);
        start.setVisibility(View.INVISIBLE);
        info = (TextView) findViewById(R.id.info);
        info.setText("Tap the red button as fast as you can for 3 seconds");
        playButton = (Button) findViewById(R.id.playbutton);
        playButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                start.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                taps.setVisibility(View.VISIBLE);
                info.setVisibility(View.INVISIBLE);
                playButton.setVisibility(View.INVISIBLE);
                i = 0;
                speed = new SpeedTapTask();
                Scheduler.getInstance().activityStart(speed);
                count = new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int seconds = (int) ((millisUntilFinished / 1000));
                        start.setText(String.valueOf(seconds));
                    }

                    public void onFinish() {
                        start.setText("");
                        imageButton.setOnClickListener(new ImageButton.OnClickListener() {

                            public void onClick(View v) {
                                i += 1;
                                taps.setText(String.valueOf(i));
                            }
                        });
                        count = new CountDownTimer(3000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                int seconds = (int) ((millisUntilFinished / 1000));
                                timer.setText(seconds + "  seconds ");
                                progressBar.setProgress((int) Math.round(millisUntilFinished / 1000.0));
                            }

                            public void onFinish() {
                                speed.setScore(i);
                                speed.setTaskLocation(MainActivity.taskLocation);
                                Scheduler.getInstance().activityStop(speed, true);

                                TapActivity.this.finish();
                            }
                        }.start();

                    }
                }.start();


            }


        });
    }
}
