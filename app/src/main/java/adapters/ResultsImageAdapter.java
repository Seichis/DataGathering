package adapters;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.performance.cognitive.datagathering.R;
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
import datastructure.StaticTaskTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ResultsImageAdapter extends BaseAdapter {
    String digitOrderLatest, digitSpanLatest, fluencyLatest, speedTapLatest, speedNumberLatest, coordinationLatest, reactionLatest, selectiveAttentionLatest, longMemoryLatest ;
    private Context context;
    private final String[] mobileValues;

    String[] digitOrderScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.digitOrder);

    String[] digitSpanScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.digitSpan);
    String[] fluencyScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.fluency);

    String[] speedTapScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.speedtap);

    String[] speedNumberScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.speednum);

    String[] coordinationScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.coord);

    String[] reactionScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.reaction);

    String[] selectiveAttentionScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.selective);

    String[] longMemoryScore = DataOperations.getInstance().prepareTaskScoreForJavascript(StaticTaskTypes.longterm);

    public ResultsImageAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.bubbles, null);
            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.item_text);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            TextView bubble = (TextView) gridView
                    .findViewById(R.id.textview1);

            String mobile = mobileValues[position];

            for (int i=0; i<9; i++ ) {
//                if (mobile.equals("Digit Order")) {
//                    bubble.setText(digitOrderScore + " round");
//                }else {
//                    bubble.setText("todo");
//                }
                switch( mobile ) {
                    case "Digit Order":
                        if (digitOrderScore.length == 0){
                            digitOrderLatest = digitOrderScore[0];
                        }else {
                            digitOrderLatest = digitOrderScore[digitOrderScore.length - 1];
                            if (Float.parseFloat(digitOrderLatest) > average(digitOrderScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(digitOrderLatest) < average(digitOrderScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(digitOrderLatest + " round");
                        break;
                    case "Digit Span":
                        if (digitSpanScore.length == 0 ){
                            digitSpanLatest = digitSpanScore[0];
                        }else{
                            digitSpanLatest = digitSpanScore[digitSpanScore.length - 1];
                            if (Float.parseFloat(digitSpanLatest) > average(digitSpanScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(digitSpanLatest) < average(digitSpanScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(digitSpanLatest + " round");
                        break;
                    case "5 Dots":
                        if (fluencyScore.length == 0){
                            fluencyLatest = fluencyScore[0];
                        }else {
                            fluencyLatest = fluencyScore[fluencyScore.length -1 ];
                            if (Float.parseFloat(fluencyLatest) > average(fluencyScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(fluencyLatest) < average(fluencyScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(fluencyLatest + " figures");
                        break;
                    case "Tap Speed":
                        if (speedTapScore.length ==0){
                            speedTapLatest = speedTapScore[0];
                        }else {
                            speedTapLatest = speedTapScore[speedTapScore.length - 1];
                            if (Float.parseFloat(speedTapLatest) > average(speedTapScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(speedTapLatest) < average(speedTapScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(speedTapLatest + " taps");
                        break;
                    case "8 Numbers":
                        if (speedNumberScore.length == 0) {
                            speedNumberLatest = speedNumberScore[0];
                        }else{
                            speedNumberLatest = speedNumberScore[speedNumberScore.length - 1];
                            if (Float.parseFloat(speedNumberLatest) > average(speedNumberScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(speedNumberLatest) < average(speedNumberScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }

                        bubble.setText(speedNumberLatest + " round");
                        break;
                    case "Bubble":
                        if (coordinationScore.length == 0){
                            coordinationLatest = coordinationScore[0];
                        }else {
                            coordinationLatest  =coordinationScore[coordinationScore.length - 1];
                            if (Float.parseFloat(coordinationLatest) > average(coordinationScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(coordinationLatest) < average(coordinationScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(coordinationLatest + " points");
                        break;
                    case "Color Tap":
                        if (reactionScore.length == 0){
                            reactionLatest = reactionScore[0];
                        }else {
                            reactionLatest = reactionScore[reactionScore.length - 1];
                            if (Float.parseFloat(reactionLatest) > average(reactionScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(reactionLatest) < average(reactionScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(reactionLatest + " sec");
                        break;
                    case "Find the T":
                        if (selectiveAttentionScore.length == 0){
                            selectiveAttentionLatest = selectiveAttentionScore[0];
                        }else {
                            selectiveAttentionLatest = selectiveAttentionScore[selectiveAttentionScore.length - 1];
                            if (Float.parseFloat(selectiveAttentionLatest) > average(selectiveAttentionScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(selectiveAttentionLatest) < average(selectiveAttentionScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(selectiveAttentionLatest + " rounds");
                        break;
                    case "10 Words":
                        if (longMemoryScore.length == 0){
                            longMemoryLatest = longMemoryScore[0];
                        }else {
                            longMemoryLatest = longMemoryScore[longMemoryScore.length - 1];
                            if (Float.parseFloat(longMemoryLatest) > average(longMemoryScore)) {
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.green));
                            }else if (Float.parseFloat(longMemoryLatest) < average(longMemoryScore)){
                                bubble.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.red));
                            }
                        }
                        bubble.setText(longMemoryLatest + " words");
                        break;
                }

            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public float average(String[] taskScore){
        int sum = 0;
        for (int i=0; i<taskScore.length; i++){
            sum = sum + Integer.parseInt(taskScore[i]);
        }
        return sum/taskScore.length;
    }

}