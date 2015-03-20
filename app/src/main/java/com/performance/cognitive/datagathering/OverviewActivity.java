package com.performance.cognitive.datagathering;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dataoperations.DataOperations;
import datastructure.AttentionTask;
import datastructure.CoordinationTask;
import datastructure.FluencyTask;
import datastructure.SpeedTask;


public class OverviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        List<String> showStats = new ArrayList<>();
        showStats.add("TODAY");
        showStats.addAll(printTodaysAttentionTasks());
        showStats.addAll(printTodaysFluencyTasks());
        showStats.addAll(printTodaysSpeedTasks());
        showStats.addAll(printTodaysCoordinationTasks());
        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, showStats);
        listview.setAdapter(adapter);
        Log.i("Stats overview", showStats.toString());

    }

    public List<String> printTodaysAttentionTasks() {

        List<AttentionTask> DL = DataOperations.getTodaysAttentionTasks();
        Log.i("Stats overview", DL.toString());
        String type ="";
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<String> toPrint = new ArrayList<String>();
        toPrint.add("Attention Tasks");
        if (!DL.isEmpty())
            for (AttentionTask ex : DL) {
                type = ex.getTaskType();
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPrint.add(type+ " task started :  " + dateStarted.get(dateStarted.HOUR_OF_DAY) + ":"
                            + dateStarted.get(dateStarted.MINUTE) + ":"
                            + dateStarted.get(dateStarted.SECOND) + ","
                            + "Score:  "+ score +"\n"+ type + " task finished :  " + dateFinished.get(dateFinished.HOUR_OF_DAY) + ":"
                            + dateFinished.get(dateFinished.MINUTE) + ":"
                            + dateFinished.get(dateFinished.SECOND) + "."
                            );
                }
            }
        return toPrint;
    }
    public List<String> printTodaysFluencyTasks() {

        List<FluencyTask> FT = DataOperations.getTodaysFluencyTasks();
        Log.i("Stats overview", FT.toString());
        String type ="";
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<String> toPrint = new ArrayList<String>();
        toPrint.add("Fluency Tasks");
        if (!FT.isEmpty())
            for (FluencyTask ex : FT) {
                type = ex.getTaskType();
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPrint.add(type+ " task started :  " + dateStarted.get(dateStarted.HOUR_OF_DAY) + ":"
                            + dateStarted.get(dateStarted.MINUTE) + ":"
                            + dateStarted.get(dateStarted.SECOND) + ","
                            + "Score:  "+ score +"\n"+ type + " task finished :  " + dateFinished.get(dateFinished.HOUR_OF_DAY) + ":"
                            + dateFinished.get(dateFinished.MINUTE) + ":"
                            + dateFinished.get(dateFinished.SECOND) + "."
                            );
                }
            }
        return toPrint;
    }
    public List<String> printTodaysSpeedTasks() {

        List<SpeedTask> ST = DataOperations.getTodaysSpeedTasks();
        Log.i("Stats overview", ST.toString());
        String type ="";
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<String> toPrint = new ArrayList<String>();
        toPrint.add("Speed Tasks");
        if (!ST.isEmpty())
            for (SpeedTask ex : ST) {
                type = ex.getTaskType();
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPrint.add(type+ " task started :  " + dateStarted.get(dateStarted.HOUR_OF_DAY) + ":"
                                    + dateStarted.get(dateStarted.MINUTE) + ":"
                                    + dateStarted.get(dateStarted.SECOND) + ","
                                    + "Score:  "+ score +"\n"+ type + " task finished :  " + dateFinished.get(dateFinished.HOUR_OF_DAY) + ":"
                                    + dateFinished.get(dateFinished.MINUTE) + ":"
                                    + dateFinished.get(dateFinished.SECOND) + "."
                    );
                }
            }
        return toPrint;
    }
    public List<String> printTodaysCoordinationTasks() {

        List<CoordinationTask> CT = DataOperations.getTodaysCoordinationTasks();
        Log.i("Stats overview", CT.toString());
        String type ="";
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<String> toPrint = new ArrayList<String>();
        toPrint.add("Coordination Tasks");
        if (!CT.isEmpty())
            for (CoordinationTask ex : CT) {
                type = ex.getTaskType();
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPrint.add(type+ " task started :  " + dateStarted.get(dateStarted.HOUR_OF_DAY) + ":"
                                    + dateStarted.get(dateStarted.MINUTE) + ":"
                                    + dateStarted.get(dateStarted.SECOND) + ","
                                    + "Score:  "+ score +"\n"+ type + " task finished :  " + dateFinished.get(dateFinished.HOUR_OF_DAY) + ":"
                                    + dateFinished.get(dateFinished.MINUTE) + ":"
                                    + dateFinished.get(dateFinished.SECOND) + "."
                    );
                }
            }
        return toPrint;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
