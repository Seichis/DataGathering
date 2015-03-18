package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import datastructure.AttentionTask;


public class DigitSpanResult extends Activity {

    TextView text3;
    EditText text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_span_result);
        text2 = (EditText) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);

        Intent intent = this.getIntent();
        Bundle bl = intent.getExtras();
        int round = bl.getInt("round");

        text2.setText("" + round);
    }

}
