package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class ResultsActivity extends Activity {

    GridView gridView;

    static final String[] TASKS = new String[] {
            "Digit Order", "Digit Span", "Bubble", "Tap Speed", "8 Numbers", "5 Dots", "Color Tap", "10 Words", "Find the T" };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        gridView = (GridView) findViewById(R.id.gridView1);

        gridView.setAdapter(new adapters.ResultsImageAdapter(this, TASKS));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        ((TextView) v.findViewById(R.id.textview1))
//                                .getText(), Toast.LENGTH_SHORT).show();
                String item =  ( ((TextView) v.findViewById(R.id.item_text)).getText()).toString();
               // Toast.makeText((TextView) v.findViewById(R.id.item_text)))
                Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                i.putExtra("item",item);
                startActivity(i);
            }
        });

    }

}