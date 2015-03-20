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

/* Change screen color */
public class ColorTapActivity extends Activity {
    private static View myView = null;
    public static final String TAG = "Score";
    public static List<Long> score = new ArrayList<Long>();
    Long tsColor;
    Long tsClick;
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
                    Intent intent = new Intent(ColorTapActivity.this, ColorTapResults.class);
                    startActivity(intent);
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

}