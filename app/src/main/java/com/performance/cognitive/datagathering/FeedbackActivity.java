package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dataoperations.DataOperations;
import datastructure.AttentionDigitSpanTask;
import datastructure.AttentionTaskDigitOrder;
import datastructure.CoordinationTask;
import datastructure.FluencyTask;
import datastructure.LongTermMemoryTask;
import datastructure.ReactionTimeTask;
import datastructure.SelectiveAttentionTask;
import datastructure.SpeedNumberTask;
import datastructure.SpeedTapTask;
import datastructure.Task;


public class FeedbackActivity extends Activity {
    String TEXT = "Cognitive aspect: ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        final WebView browser;
        TextView taskTitle;
        final TextView infoText;
        TextView taskAspect;
        final Button info;
        final Button overview;
        taskTitle = (TextView) findViewById(R.id.taskTitle);
        taskAspect = (TextView) findViewById(R.id.taskAspect);
        infoText = (TextView) findViewById(R.id.infoText);
        infoText.setVisibility(View.INVISIBLE);
        info = (Button) findViewById(R.id.buttonInfo);
        overview = (Button) findViewById(R.id.overview);
        Bundle extras = getIntent().getExtras();
        String task = extras.getString("item");
        switch (task) {
            case "Digit Order":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Working memory");
                infoText.setText(getResources().getString(R.string.digit_order));
                break;
            case "Digit Span":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Attention");
                break;
            case "5 Dots":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Fluency");
                break;
            case "Tap Speed":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Speed");
                break;
            case "8 Numbers":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Speed");
                break;
            case "Bubble":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Coordination");
                break;
            case "Color Tap":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Reaction time");
                break;
            case "Find the T":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Selective attention");
                break;
            case "10 Words":
                taskTitle.setText(task);
                taskAspect.setText(TEXT + "Long-term memory");
                break;
        }
        browser=(WebView)findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setSupportZoom(true);
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        browser.loadUrl("file:///android_res/raw/googlechart.html");
        info.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                browser.setVisibility(View.INVISIBLE);
                overview.setBackgroundColor(Color.BLACK);
                info.setBackgroundColor(Color.parseColor("#2196f3"));
                overview.setTextColor(Color.parseColor("#2196f3"));
                info.setTextColor(Color.BLACK);
                infoText.setVisibility(View.VISIBLE);
            }
        });
        overview.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                browser.setVisibility(View.VISIBLE);
                overview.setBackgroundColor(Color.parseColor("#2196f3"));
                overview.setTextColor(Color.BLACK);
                info.setBackgroundColor(Color.BLACK);
                info.setTextColor(Color.parseColor("#2196f3"));
                infoText.setVisibility(View.INVISIBLE);
            }
        });

    }
    public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getData() {

            Bundle extras = getIntent().getExtras();
            String task = extras.getString("item");
            switch (task) {
                case "Digit Order":
                    return plotAttentionDigitOrderTasks().toString();
                case "Digit Span":
                    return a1dToJson(plotAttentionDigitSpanTasks()).toString();
                case "5 Dots":
                    return a1dToJson(plotFluencyTasks()).toString();
                case "Tap Speed":
                    return a1dToJson(plotSpeedTapTasks()).toString();
                case "8 Numbers":
                    return a1dToJson(plotSpeedNumberTasks()).toString();
                case "Bubble":
                    return a1dToJson(plotCoordinationTasks()).toString();
                case "Color Tap":
                    return a1dToJson(plotReactionTimeTasks()).toString();
                case "Find the T":
                    return a1dToJson(plotSelectiveAttentionTasks()).toString();
                case "10 Words":
                    return a1dToJson(plotLongTermMemoryTasks()).toString();
                default:
                    return a1dToJson(plotLongTermMemoryTasks()).toString();
            }
        }

    }
    public int[] listToArray(List<Integer> score){
        int[] array = new int[score.size()];
        for(int i = 0; i < score.size(); i++) array[i] = score.get(i);
        return array;
    }
    public JSONArray plotAttentionDigitOrderTasks() {

        List<AttentionTaskDigitOrder> DL = DataOperations.getInstance().getTodaysAttentionDigitOrderTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        JSONObject ret = new JSONObject();
        JSONArray arr = new JSONArray();
        if (!DL.isEmpty())
            for (AttentionTaskDigitOrder ex : DL) {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    arr.put(score);
                }
            }
        Log.i("List to arr", "" + listToArray(toPlot));
        return arr;
    }
    public int[] plotAttentionDigitSpanTasks() {

        List<AttentionDigitSpanTask> DL = DataOperations.getInstance().getTodaysAttentionDigitSpanTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (AttentionDigitSpanTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }

    public int[] plotFluencyTasks() {

        List<FluencyTask> DL = DataOperations.getInstance().getTodaysFluencyTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (FluencyTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotSpeedTapTasks() {

        List<SpeedTapTask> DL = DataOperations.getInstance().getTodaysSpeedTapTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (SpeedTapTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotSpeedNumberTasks() {

        List<SpeedNumberTask> DL = DataOperations.getInstance().getTodaysSpeedNumberTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (SpeedNumberTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotCoordinationTasks() {

        List<CoordinationTask> DL = DataOperations.getInstance().getTodaysCoordinationTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (CoordinationTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotReactionTimeTasks() {

        List<ReactionTimeTask> DL = DataOperations.getInstance().getTodaysReactionTimeTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (ReactionTimeTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotSelectiveAttentionTasks() {

        List<SelectiveAttentionTask> DL = DataOperations.getInstance().getTodaysSelectiveAttentionTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (SelectiveAttentionTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
    public int[] plotLongTermMemoryTasks() {

        List<LongTermMemoryTask> DL = DataOperations.getInstance().getTodaysLongTermMemoryTasks();
        GregorianCalendar dateStarted,dateFinished;
        int score=0;
        List<Integer> toPlot = new ArrayList<>();
        if (!DL.isEmpty())
            for (LongTermMemoryTask ex : DL)
            {
                dateStarted = ex.getStartTimestamp();
                dateFinished = ex.getEndTimestamp();
                score=ex.getScore();
                if (dateStarted != null && dateFinished!=null) {
                    toPlot.add(score);
                }
            }
        return listToArray(toPlot);
    }
//    public int[] plotTasks() {
//        List<AttentionTaskDigitOrder> DL = DataOperations.getInstance().getTodaysAttentionDigitOrderTasks();
//        if (!DL.isEmpty())
//            for (Task ex : DL) {
//                String type = ex.getTaskType();
//
//            }
//        return
//    }
    private String a1dToJson(int[] data) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            int d = data[i];
            if (i > 0)
                sb.append(",");
            sb.append(d);
        }
        sb.append("]");
        return sb.toString();
    }



}
