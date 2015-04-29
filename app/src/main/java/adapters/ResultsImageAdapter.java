package adapters;

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.performance.cognitive.datagathering.R;
//import dataoperations.DataOperations;
//import datastructure.AttentionDigitSpanTask;
//import datastructure.AttentionTaskDigitOrder;
//import datastructure.CoordinationTask;
//import datastructure.FluencyTask;
//import datastructure.LongTermMemoryTask;
//import datastructure.ReactionTimeTask;
//import datastructure.SelectiveAttentionTask;
//import datastructure.SpeedNumberTask;
//import datastructure.SpeedTapTask;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//public class ResultsImageAdapter extends BaseAdapter {
//    private Context context;
//    private final String[] mobileValues;
//    String digitOrderScore = plotAttentionDigitOrderTasks();
//    String digitSpanScore = plotAttentionDigitSpanTasks();
//    String fluencyScore = plotFluencyTasks();
//    String speedTapScore = plotSpeedTapTasks();
//    String speedNumber = plotSpeedNumberTasks();
//    String coordination = plotCoordinationTasks();
//    String reaction = plotReactionTimeTasks();
//    String selectiveAttention = plotSelectiveAttentionTasks();
//    String longMemory = plotLongTermMemoryTasks();
//    public ImageAdapter(Context context, String[] mobileValues) {
//        this.context = context;
//        this.mobileValues = mobileValues;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View gridView;
//
//        if (convertView == null) {
//
//            gridView = new View(context);
//
//            // get layout from mobile.xml
//            gridView = inflater.inflate(R.layout.bubbles, null);
//
//            // set value into textview
//            TextView textView = (TextView) gridView
//                    .findViewById(R.id.item_text);
//            textView.setText(mobileValues[position]);
//
//            // set image based on selected text
//            TextView imageView = (TextView) gridView
//                    .findViewById(R.id.textview1);
//
//            String mobile = mobileValues[position];
//            for (int i=0; i<9; i++ ) {
//                if (mobile.equals("Digit Order")) {
//                    imageView.setText(digitOrderScore + " round");
//                }else {
//                    imageView.setText("todo");
//                }
//                switch( mobile ) {
//                    case "Digit Order":
//                        imageView.setText(digitOrderScore + " round");
//                        break;
//                    case "Digit Span":
//                        imageView.setText(digitSpanScore + " round");
//                        break;
//                    case "5 Dots":
//                        imageView.setText(fluencyScore + " figures");
//                        break;
//                    case "Tap Speed":
//                        imageView.setText(speedTapScore + " taps");
//                        break;
//                    case "8 Numbers":
//                        imageView.setText(speedNumber + " round");
//                        break;
//                    case "Bubble":
//                        imageView.setText(coordination + " points");
//                        break;
//                    case "Color Tap":
//                        imageView.setText(reaction + " sec");
//                        break;
//                    case "Find the T":
//                        imageView.setText(selectiveAttention + " rounds");
//                        break;
//                    case "10 Words":
//                        imageView.setText(longMemory + " words");
//                        break;
//                }
//
//            }
//
//        } else {
//            gridView = (View) convertView;
//        }
//
//        return gridView;
//    }
//
//    @Override
//    public int getCount() {
//        return mobileValues.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//    public String plotAttentionDigitOrderTasks() {
//
//        List<AttentionTaskDigitOrder> DL = DataOperations.getTodaysAttentionDigitOrderTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (AttentionTaskDigitOrder ex : DL) {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//    public String plotAttentionDigitSpanTasks() {
//
//        List<AttentionDigitSpanTask> DL = DataOperations.getTodaysAttentionDigitSpanTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (AttentionDigitSpanTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotFluencyTasks() {
//
//        List<FluencyTask> DL = DataOperations.getTodaysFluencyTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (FluencyTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotSpeedTapTasks() {
//
//        List<SpeedTapTask> DL = DataOperations.getTodaysSpeedTapTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (SpeedTapTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotSpeedNumberTasks() {
//
//        List<SpeedNumberTask> DL = DataOperations.getTodaysSpeedNumberTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (SpeedNumberTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotCoordinationTasks() {
//
//        List<CoordinationTask> DL = DataOperations.getTodaysCoordinationTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (CoordinationTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotReactionTimeTasks() {
//
//        List<ReactionTimeTask> DL = DataOperations.getTodaysReactionTimeTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (ReactionTimeTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotSelectiveAttentionTasks() {
//
//        List<SelectiveAttentionTask> DL = DataOperations.getTodaysSelectiveAttentionTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (SelectiveAttentionTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//    public String plotLongTermMemoryTasks() {
//
//        List<LongTermMemoryTask> DL = DataOperations.getTodaysLongTermMemoryTasks();
//        GregorianCalendar dateStarted,dateFinished;
//        int score=0;
//        List<Integer> toPlot = new ArrayList<>();
//        if (!DL.isEmpty())
//            for (LongTermMemoryTask ex : DL)
//            {
//                dateStarted = ex.getStartTimestamp();
//                dateFinished = ex.getEndTimestamp();
//                score=ex.getScore();
//                if (dateStarted != null && dateFinished!=null) {
//                    toPlot.add(score);
//                }
//            }
//        return toPlot.get(toPlot.size()-1).toString();
//    }
//
//}