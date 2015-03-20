package com.performance.cognitive.datagathering;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;


public class ColorTapResults extends ActionBarActivity {
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_tap_results);
        score = (TextView) findViewById(R.id.score);
        score.setText("Your Average Reaction Time is " + String.valueOf(Average(ColorTapActivity.score)) + " msec.");

    }

    private double Average(List<Long> score) {
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
