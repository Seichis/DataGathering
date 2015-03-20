package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import datastructure.ReactionTimeTask;
import scheduler.Scheduler;

/* Change screen color */
public class ColorTapActivity extends Activity {
    private static View myView = null;
    public static final String TAG = "Score";
    List<Long> score = new ArrayList<>();
    Long tsColor;
    Long tsClick;
    ReactionTimeTask reaction;
    int currentColor = 0;
    int i = 0;
    Random random = new Random();
    long startTime = System.currentTimeMillis();
    long time;
    static final int[] COLORS = {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.GRAY, Color.MAGENTA};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_tap);
        myView = (View) findViewById(R.id.my_view);
        time = System.currentTimeMillis() - startTime;
        reaction = new ReactionTimeTask();
        Scheduler.getInstance().activityStart(reaction);
        new Thread(new Runnable() {
            public void run() {
                while (time <= 30000) {
                    i = random.nextInt(3000) + 1000;
                    try {
                        Thread.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    updateColor();
                    time = System.currentTimeMillis() - startTime;

                }
                if (time > 30000) {
                    //Thread.yield();
//                    Intent intent = new Intent(ColorTapActivity.this, ColorTapResults.class);
//                    startActivity(intent);
                    reaction.setScore((int)(averageScore(score)*1000));
                    ColorTapActivity.this.finish();
                }
            }
        }).start();
    }


    private void updateColor() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (currentColor > COLORS.length - 1) {
                    currentColor = 0;

                }
                myView.setBackgroundColor(COLORS[currentColor]);
                tsColor = System.currentTimeMillis();
                myView.setOnClickListener(new ImageButton.OnClickListener() {

                    public void onClick(View v) {
                        tsClick = System.currentTimeMillis();
                        score.add(tsClick - tsColor);
                        //Log.e(TAG, String.valueOf(score));
                    }
                });
                currentColor += 1;
            }


        });
    }
    private double averageScore(List<Long> score) {
        Long sum=0L;
        if(!score.isEmpty()) {
            for (Long i : score) {
                sum += i;
            }
            return sum.doubleValue() / score.size();
        }
        return sum;
    }
}