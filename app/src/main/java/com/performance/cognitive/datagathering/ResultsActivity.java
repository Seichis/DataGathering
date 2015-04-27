//package com.performance.cognitive.datagathering;
//
//import com.performance.cognitive.datagathering.adapter.ImageAdapter;
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.view.View;
//import android.widget.AdapterView.OnItemClickListener;
//
//public class ResultsActivity extends Activity {
//
//    GridView gridView;
//
//    static final String[] TASKS = new String[] {
//            "Digit Order", "Digit Span", "Bubble", "Tap Speed", "8 Numbers", "5 Dots", "Color Tap", "10 Words", "Find the T" };
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_results);
//
//        gridView = (GridView) findViewById(R.id.gridView1);
//
//        gridView.setAdapter(new ImageAdapter(this, TASKS));
//
//        gridView.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        ((TextView) v.findViewById(R.id.item_text))
//                                .getText(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//}