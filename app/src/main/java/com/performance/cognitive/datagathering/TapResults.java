package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Ioanna on 3/6/2015.
 */
public class TapResults extends Activity {
    TextView score;
    int taps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_results);
        score = (TextView) findViewById(R.id.score);
        score.setText(String.valueOf(TapActivity.i) + " taps in 3 seconds!");

    }

}