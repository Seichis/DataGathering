package com.performance.cognitive.datagathering;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
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
    SpeedTapTask speed;
    public static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        imageButton = (ImageView) findViewById(R.id.imageButton);
        timer = (TextView) findViewById(R.id.timer);
        taps = (TextView) findViewById(R.id.taps);
        start = (TextView) findViewById(R.id.start);
        i = 0;
        speed=new SpeedTapTask();
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

                        Scheduler.getInstance().activityStop(speed, true);
//                        Intent intent = new Intent(TapActivity.this, TapResults.class);
//                        startActivity(intent);
                        TapActivity.this.finish();
                    }
                }.start();

            }
        }.start();



    }
}
