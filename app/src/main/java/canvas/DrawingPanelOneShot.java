package canvas;

/**
 * Created by User1 on 17/3/2015.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.performance.cognitive.datagathering.ConnectDotsOneShotActivity;
import com.performance.cognitive.datagathering.TrailMakingActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by User1 on 14/3/2015.
 */
public class DrawingPanelOneShot extends View implements View.OnTouchListener {
    private static final String TAG = "DrawViewOneShot";
    int count;
    private static final float MINP = 0.25f;
    private static final float MAXP = 0.75f;
    private List<ShapeDrawable> dotsToDraw;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private List<Path> pathsToReset = new ArrayList<>();
    private List<Path> pathsToStay = new ArrayList<>();
    private Path mPathStay;
    private Paint textPaint;
    int dotId;
    public static int score = 0;
    boolean numberSpotted;
    private Set<ShapeDrawable> changedDotSet;
    private static List<Float> measurePath = new ArrayList<>();
    static Set<List<Float>> pmSetList = new HashSet<>();
    Matrix mMatrix;
    Path mergedPath = new Path();
    private Paint barPaint;
    private float barStartX = 20;
    private float barStartY = 20;
    private float barEndX;
    private float barEndY = 30;
    private float currentSeekBarLength = 0;
    Point mPoint = new Point();
    public WindowManager wm;

    public DrawingPanelOneShot(Context context) {
        super(context);
        wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(mPoint);
        changedDotSet = new HashSet<>();
        mPathStay = new Path();
        numberSpotted = false;
        count = 0;
        dotId = 0;
        mMatrix = new Matrix();
        mMatrix.setTranslate(0f, 0f);
        dotsToDraw = new ArrayList<>();
        textPaint = new Paint();
        barPaint = new Paint();
        mPaint = new Paint();
        mCanvas = new Canvas();
        mPath = new Path();
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        setTextPaint(textPaint);
        setDotPaint(mPaint);
        pathsToReset.add(mPath);
        pathsToStay.add(mPathStay);
        dotsToDraw = getDotList(context);
    }

    private void setDotPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);
    }

    public void setTextPaint(Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(40f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(Integer.toString(score),mPoint.x/2 ,mPoint.y/8 , textPaint);
        for (ShapeDrawable dot : dotsToDraw) {
            dot.draw(canvas);
            canvas.drawText(Integer.toString(dotsToDraw.indexOf(dot) + 1), dot.getBounds().centerX(), dot.getBounds().centerY(), textPaint);
        }
        for (Path p : pathsToReset) {
            canvas.drawPath(p, mPaint);
        }
        for (Path p : pathsToStay) {
            canvas.drawPath(p, mPaint);
        }
        barEndX = this.getWidth() - 20;
        barPaint.setColor(Color.WHITE);
        setCurrentSeekBarLength((barEndX - barStartX) * (ConnectDotsOneShotActivity.second / 30));
        barPaint.setColor(Color.BLUE);
        canvas.drawRect(barStartX, barStartY, getCurrentSeekBarLength(),
                barEndY, barPaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {

        mPath.moveTo(x, y);
        mPathStay.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        checkPath((int) x, (int) y);


        mCanvas.drawPath(mPathStay, mPaint);

    }

    private void resetLevel() {
        ConnectDotsOneShotActivity.timeToReset = true;

    }

    private void touch_up() {
        mPath.reset();
    }

    float startX = 0;
    float startY = 0;
    float stopX = 0;
    float stopY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();

                touch_start(startX, startY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                float totalPathLength = 0;
                List<ShapeDrawable> changedDotList = new ArrayList<>(changedDotSet);
                stopX = event.getX();
                stopY = event.getY();
                touch_up();
                PathMeasure pmMerged;
                pmMerged = new PathMeasure();
                //mergedPath.close();
                for (Path pts : pathsToStay) {
                    pmMerged.setPath(pts, false);
                    totalPathLength += pmMerged.getLength();
                }
//                for (ShapeDrawable sd : changedDotList) {
//                    measurePath.add((float) dotsToDraw.indexOf(sd) + 10);
//                }
                if (totalPathLength > 100) {
                    measurePath.add((float) Math.round(totalPathLength * 100));
                    //Collections.sort(measurePath);

                    if (!measurePath.isEmpty()) {

                        Log.i("Set of lists", "    " + measurePath);
                        pmSetList.add(measurePath);
                        measurePath.clear();

                    }
                    Log.i("Set of lists", "    " + pmSetList.size() + "    ");
                }
                score = pmSetList.size();
                resetLevel();

                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }

    private List<ShapeDrawable> getDotList(Context context) {
        List<ShapeDrawable> dotList = new ArrayList<>();
        List<Point> fixedPointList;
        int diameter = 100;
        fixedPointList = createFixedPoints(context);

        for (Point p : fixedPointList) {
            dotList.add(createDot(p.x, p.y, diameter));

        }

        return dotList;
    }

    private List<Point> createFixedPoints(Context context) {

        List<Point> pointList = new ArrayList<>();
        Point mPointA = new Point();
        Point mPointB = new Point();
        Point mPointC = new Point();
        Point mPointD = new Point();
        Point mPointE = new Point();
        int width;
        int height;

        // Set Point A of the rectangle

        width = (mPoint.x / 4);
        height = (mPoint.y / 4);
        mPointA.set(width, height);
        pointList.add(mPointA);

        // Set Point B of the rectangle

        width = (3 * mPoint.x / 4);
        height = (mPoint.y / 4);
        mPointB.set(width, height);
        pointList.add(mPointB);

        // Set Point C of the rectangle

        width = (mPoint.x / 4);
        height = (3 * mPoint.y / 4);
        mPointC.set(width, height);
        pointList.add(mPointC);

        // Set Point D of the rectangle

        width = (3 * mPoint.x / 4);
        height = (3 * mPoint.y / 4);
        mPointD.set(width, height);

        pointList.add(mPointD);

        // Set Point E in the center of the rectangle

        width = (mPoint.x / 2);
        height = (mPoint.y / 2);
        mPointE.set(width, height);
        pointList.add(mPointE);
        return pointList;
    }

    private ShapeDrawable createDot(int width, int height, int diameter) {
        ShapeDrawable dot = new ShapeDrawable(new OvalShape());
        dot.setBounds(width - 50, height, width + diameter, height + diameter);
        dot.getPaint().setColor(0xff74AC23);
        return dot;
    }

    private void setCurrentSeekBarLength(float x) {

        this.currentSeekBarLength = x;
    }

    private float getCurrentSeekBarLength() {

        return this.currentSeekBarLength;
    }

    private void checkPath(int x, int y) {
        double firstTapTimestamp = 0d;
        double secondTapTimestamp = 0d;
        PathMeasure pm;

        pm = new PathMeasure(mPathStay, false);


        for (ShapeDrawable dot : dotsToDraw) {
            if (Math.abs(x - dot.getBounds().exactCenterX()) < 90 && Math.abs(y - dot.getBounds().exactCenterY()) < 90) {


                if (!changedDotSet.contains(dot)) {
                    firstTapTimestamp = System.currentTimeMillis();
                    dot.setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.DARKEN);
                    if (count == 0) {
                        changedDotSet.clear();
                        startX = dot.getBounds().centerX();
                        startY = dot.getBounds().centerY();
                        count++;
                    }
                    changedDotSet.add(dot);
                    mPathStay.moveTo(startX, startY);
                    mPathStay.lineTo(dot.getBounds().centerX(), dot.getBounds().centerY());

                    mergedPath.addPath(mPathStay);
                    //pm = new PathMeasure(mergedPath, false);

                    mPathStay = new Path();
                    pathsToStay.add(mPathStay);

                    startX = dot.getBounds().centerX();
                    startY = dot.getBounds().centerY();

                    numberSpotted = true;
                } else if (changedDotSet.contains(dot) && pm.getLength() > 100) {
                    secondTapTimestamp = System.currentTimeMillis();
                    if (secondTapTimestamp - firstTapTimestamp > 100) {
                        resetLevel();
                    }
                }
            }
        }
    }
}

