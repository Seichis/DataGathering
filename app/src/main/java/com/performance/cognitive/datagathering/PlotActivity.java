//package com.performance.cognitive.datagathering;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.Random;
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PointF;
//import android.os.Bundle;
//import android.util.FloatMath;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//import android.widget.Button;
//
//import com.androidplot.Plot;
//import com.androidplot.xy.BoundaryMode;
//import com.androidplot.xy.LineAndPointFormatter;
//import com.androidplot.xy.SimpleXYSeries;
//import com.androidplot.xy.XYPlot;
//import com.androidplot.xy.XYSeries;
//
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
///***********************************
// * @author David Buezas (david.buezas at gmail.com)
// * Feel free to copy, modify and use the source as it fits you.
// * 09/27/2012 nfellows - updated for 0.5.1 and made a few simplifications
// */
//public class PlotActivity extends Activity implements OnTouchListener {
//    private static final int SERIES_SIZE = 200;
//    private XYPlot mySimpleXYPlot;
//    private Button resetButton;
//    private SimpleXYSeries[] series = null;
//    private PointF minXY;
//    private PointF maxXY;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_plot);
//        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
//        XYSeries series1 = new SimpleXYSeries(
//                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
//                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
//                "Series1");
//        resetButton = (Button) findViewById(R.id.resetButton);
//        resetButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                minXY.x = series[0].getX(0).floatValue();
//                maxXY.x = series[3].getX(series[3].size() - 1).floatValue();
//                mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x, BoundaryMode.FIXED);
//
//                // pre 0.5.1 users should use postRedraw() instead.
//                mySimpleXYPlot.redraw();
//            }
//        });
//        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
//        mySimpleXYPlot.setOnTouchListener(this);
//        mySimpleXYPlot.getGraphWidget().setTicksPerRangeLabel(2);
//        mySimpleXYPlot.getGraphWidget().setTicksPerDomainLabel(2);
//        mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setColor(Color.BLACK);
//        mySimpleXYPlot.getGraphWidget().setRangeValueFormat(
//                new DecimalFormat("#####"));
//        mySimpleXYPlot.getGraphWidget().setDomainValueFormat(
//                new DecimalFormat("#####.#"));
//        mySimpleXYPlot.getGraphWidget().setRangeLabelWidth(25);
//        mySimpleXYPlot.setRangeLabel("");
//        mySimpleXYPlot.setDomainLabel("");
//        //mySimpleXYPlot.getGraphWidget().getDomainLabelPaint().setColor(Color.BLACK);
//        mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
//        //mySimpleXYPlot.disableAllMarkup();
//        series = new SimpleXYSeries[4];
//        int scale = 1;
//        for (int i = 0; i < 4; i++, scale *= 5) {
//            series[i] = new SimpleXYSeries("S" + i);
//            populateSeries(series[i], scale);
//        }
//        mySimpleXYPlot.addSeries(series[3],
//                new LineAndPointFormatter(Color.rgb(50, 0, 0), null,
//                        Color.rgb(100, 0, 0), null));
//
//        mySimpleXYPlot.redraw();
//        mySimpleXYPlot.calculateMinMaxVals();
//        minXY = new PointF(mySimpleXYPlot.getCalculatedMinX().floatValue(),
//                mySimpleXYPlot.getCalculatedMinY().floatValue());
//        maxXY = new PointF(mySimpleXYPlot.getCalculatedMaxX().floatValue(),
//                mySimpleXYPlot.getCalculatedMaxY().floatValue());
//    }
//
//    private void populateSeries(SimpleXYSeries series, int max) {
//        //Random r = new Random();
//        //Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
//        List<Integer> taskSeries = plotAttentionDigitOrderTasks();
//        List<Integer> digitSpanSeries = plotAttentionDigitSpanTasks();
//        List<Integer> fluencySeries = plotFluencyTasks();
//        List<Integer> speedTapSeries = plotSpeedTapTasks();
//        List <Integer> speedNumberSeries = plotSpeedNumberTasks();
//        List<Integer> coordinationSeries = plotCoordinationTasks();
//        List<Integer> reactionSeries = plotReactionTimeTasks();
//        List<Integer> selectiveAttentionSeries = plotSelectiveAttentionTasks();
//        List <Integer> longMemorySeries = plotLongTermMemoryTasks();
//        Bundle extras = getIntent().getExtras();
//        String task = extras.getString("item");
//
//            switch( task ) {
//                case "Digit Order":
//                    for (int i = 0; i < taskSeries.size(); i++) {
//                        series.addLast(i, taskSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Digit Order task");
//                    break;
//                case "Digit Span":
//                    for (int i = 0; i < digitSpanSeries.size(); i++) {
//                        series.addLast(i, digitSpanSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Digit Span task");
//                    break;
//                case "Fluency":
//                    for (int i = 0; i < fluencySeries.size(); i++) {
//                        series.addLast(i, fluencySeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Fluency task");
//                    break;
//                case "Tap Speed":
//                    for (int i = 0; i < speedTapSeries.size(); i++) {
//                        series.addLast(i, speedTapSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Tap Speed task");
//                    break;
//                case "Number Speed":
//                    for (int i = 0; i < speedNumberSeries.size(); i++) {
//                        series.addLast(i, speedNumberSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Number Speed task");
//                    break;
//                case "Coordination":
//                    for (int i = 0; i < coordinationSeries.size(); i++) {
//                        series.addLast(i, coordinationSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Coordination Task");
//                    break;
//                case "Reaction Time":
//                    for (int i = 0; i < reactionSeries.size(); i++) {
//                        series.addLast(i, reactionSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Reaction Time task");
//                    break;
//                case "Selective Attention":
//                    for (int i = 0; i < selectiveAttentionSeries.size(); i++) {
//                        series.addLast(i, selectiveAttentionSeries.get(i));
//                    }
//                    mySimpleXYPlot.setTitle("Selective Attention task");
//                    break;
//                case "Long Term Memory":
//                    for (int i = 0; i < longMemorySeries.size(); i++) {
//                        series.addLast(i, longMemorySeries.get(i));
//                }
//                mySimpleXYPlot.setTitle("Long-Term Memory task");
//                break;
//        }
//    }
//    public List<Integer> plotAttentionDigitOrderTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotAttentionDigitSpanTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotFluencyTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotSpeedTapTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotSpeedNumberTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotCoordinationTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotReactionTimeTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotSelectiveAttentionTasks() {
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
//        return toPlot;
//    }
//
//    public List<Integer> plotLongTermMemoryTasks() {
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
//        return toPlot;
//    }
//
//    // Definition of the touch states
//    static final int NONE = 0;
//    static final int ONE_FINGER_DRAG = 1;
//    static final int TWO_FINGERS_DRAG = 2;
//    int mode = NONE;
//
//    PointF firstFinger;
//    float distBetweenFingers;
//    boolean stopThread = false;
//
//    @Override
//    public boolean onTouch(View arg0, MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN: // Start gesture
//                firstFinger = new PointF(event.getX(), event.getY());
//                mode = ONE_FINGER_DRAG;
//                stopThread = true;
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//                mode = NONE;
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN: // second finger
//                distBetweenFingers = spacing(event);
//                // the distance check is done to avoid false alarms
//                if (distBetweenFingers > 5f) {
//                    mode = TWO_FINGERS_DRAG;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mode == ONE_FINGER_DRAG) {
//                    PointF oldFirstFinger = firstFinger;
//                    firstFinger = new PointF(event.getX(), event.getY());
//                    scroll(oldFirstFinger.x - firstFinger.x);
//                    mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x,
//                            BoundaryMode.FIXED);
//                    mySimpleXYPlot.redraw();
//
//                } else if (mode == TWO_FINGERS_DRAG) {
//                    float oldDist = distBetweenFingers;
//                    distBetweenFingers = spacing(event);
//                    zoom(oldDist / distBetweenFingers);
//                    mySimpleXYPlot.setDomainBoundaries(minXY.x, maxXY.x,
//                            BoundaryMode.FIXED);
//                    mySimpleXYPlot.redraw();
//                }
//                break;
//        }
//        return true;
//    }
//
//    private void zoom(float scale) {
//        float domainSpan = maxXY.x - minXY.x;
//        float domainMidPoint = maxXY.x - domainSpan / 2.0f;
//        float offset = domainSpan * scale / 2.0f;
//
//        minXY.x = domainMidPoint - offset;
//        maxXY.x = domainMidPoint + offset;
//
//        minXY.x = Math.min(minXY.x, series[3].getX(series[3].size() - 3)
//                .floatValue());
//        maxXY.x = Math.max(maxXY.x, series[0].getX(1).floatValue());
//        clampToDomainBounds(domainSpan);
//    }
//
//    private void scroll(float pan) {
//        float domainSpan = maxXY.x - minXY.x;
//        float step = domainSpan / mySimpleXYPlot.getWidth();
//        float offset = pan * step;
//        minXY.x = minXY.x + offset;
//        maxXY.x = maxXY.x + offset;
//        clampToDomainBounds(domainSpan);
//    }
//
//    private void clampToDomainBounds(float domainSpan) {
//        float leftBoundary = series[0].getX(0).floatValue();
//        float rightBoundary = series[3].getX(series[3].size() - 1).floatValue();
//        // enforce left scroll boundary:
//        if (minXY.x < leftBoundary) {
//            minXY.x = leftBoundary;
//            maxXY.x = leftBoundary + domainSpan;
//        } else if (maxXY.x > series[3].getX(series[3].size() - 1).floatValue()) {
//            maxXY.x = rightBoundary;
//            minXY.x = rightBoundary - domainSpan;
//        }
//    }
//
//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return FloatMath.sqrt(x * x + y * y);
//    }
//}