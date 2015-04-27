package com.performance.cognitive.datagathering;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PlotList extends ListActivity {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        String[] values = new String[] { "Digit Order", "Digit Span", "5 Dots",
                "Tap Speed", "8 Numbers", "Bubble", "Color Tap", "Find the T",
                "10 Words" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
        i.putExtra("item",item);
        startActivity(i);
    }
}