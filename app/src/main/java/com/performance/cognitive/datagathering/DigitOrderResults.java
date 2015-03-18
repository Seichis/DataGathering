package com.performance.cognitive.datagathering;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;



public class DigitOrderResults extends ActionBarActivity {
    TextView results;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_order_results);
        results = (TextView)findViewById(R.id.doresults);
        counter=0;
        for (int x=0; x<10; x++){
            if (DigitOrder.userResults.get(x).equals( DigitOrder.digitsResults.get(x))) {
                counter+=1;
            }
        }
        results.setText("Correct: "+String.valueOf(counter)+" out of 10");
    }



}
