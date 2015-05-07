package com.performance.cognitive.datagathering;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import dataoperations.DataOperations;
import datastructure.StaticTaskTypes;


public class FeedbackActivity extends ActionBarActivity {
    String TEXT = "Cognitive aspect: ";
    Bundle extras;
    WebView browser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras=getIntent().getExtras();
        setContentView(R.layout.activity_feedback);

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
        browser = (WebView) findViewById(R.id.webkit);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setSupportZoom(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        browser.loadUrl("file:///android_res/raw/googlechart.html");
        info.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                browser.setVisibility(View.INVISIBLE);
                overview.setBackgroundColor(Color.parseColor("#ff9800"));
                info.setBackgroundColor(Color.parseColor("#2196f3"));
                infoText.setVisibility(View.VISIBLE);
            }
        });
        overview.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                browser.setVisibility(View.VISIBLE);
                overview.setBackgroundColor(Color.parseColor("#2196f3"));
                info.setBackgroundColor(Color.parseColor("#ff9800"));
                infoText.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        browser.destroy();
    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getData() {


            String task = extras.getString("item");
            switch (task) {
                case "Digit Order":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.digitOrder));
                case "Digit Span":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.digitSpan));
                case "5 Dots":
                    Log.i("dots"," "+DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.fluency)));
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.fluency));
                case "Tap Speed":
                    Log.i("tapp", DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.speedtap)));
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.speedtap));
                case "8 Numbers":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.speednum));
                case "Bubble":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.coord));
                case "Color Tap":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.reaction));
                case "Find the T":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.selective));
                case "10 Words":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.longterm));
                default:
                    return "Nothing to show";
            }
        }

        @JavascriptInterface
        public String getWorst() {

            String task = extras.getString("item");
            switch (task) {
                case "Digit Order":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.digitOrder));
                case "Digit Span":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.digitSpan));
                case "5 Dots":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.fluency));
                case "Tap Speed":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.speedtap));
                case "8 Numbers":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.speednum));
                case "Bubble":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.coord));
                case "Color Tap":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.reaction));
                case "Find the T":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.selective));
                case "10 Words":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskWorstScoreForJavascript(StaticTaskTypes.longterm));
                default:
                    return "Nothing to show";
            }
        }
        @JavascriptInterface
        public String getBest() {

            String task = extras.getString("item");
            switch (task) {
                case "Digit Order":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.digitOrder));
                case "Digit Span":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.digitSpan));
                case "5 Dots":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.fluency));
                case "Tap Speed":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.speedtap));
                case "8 Numbers":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.speednum));
                case "Bubble":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.coord));
                case "Color Tap":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.reaction));
                case "Find the T":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.selective));
                case "10 Words":
                    return DataOperations.getInstance().javascriptFeedbackToJson(DataOperations.getInstance().prepareTaskBestScoreForJavascript(StaticTaskTypes.longterm));
                default:
                    return "Nothing to show";
            }
        }
    }


}
