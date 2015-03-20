package canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by User1 on 14/3/2015.
 */
public class DrawingPanel extends View implements View.OnTouchListener {
    private static final String TAG = "DrawView";
    static int count = 0;

    private List<ShapeDrawable> dotsToDraw;
    private Canvas mCanvas;
    private Path mPath;
    Paint barPaint;
    CustomProgressBar mCustomProgressBar;
    //    private Paint mPaint;
    private List<Path> pathsToReset = new ArrayList<>();
    private List<Path> pathsToStay = new ArrayList<>();
    private Path mPathStay;
    private Paint textPaint;
    int dotId;
    boolean numberSpotted;

    public DrawingPanel(Context context) {
        super(context);
        mPathStay = new Path();
        numberSpotted = false;
        count = 0;
        dotId = 0;
        dotsToDraw = new ArrayList<>();
        textPaint = new Paint();
        barPaint = new Paint();
        mCustomProgressBar=new CustomProgressBar(20,20,350,40);
//        mPaint = new Paint();
        mCanvas = new Canvas();
        mPath = new Path();
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        setTextPaint(textPaint);
//        setDotPaint(mPaint);
        pathsToReset.add(mPath);
        pathsToStay.add(mPathStay);
        dotsToDraw = getDotList(context);
    }

//    private void setDotPaint(Paint paint) {
//        paint.setAntiAlias(true);
//        paint.setDither(true);
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.ROUND);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth(20);
//    }

    public void setTextPaint(Paint paint) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(40f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ShapeDrawable dot : dotsToDraw) {
            dot.draw(canvas);
            canvas.drawText(Integer.toString(dotsToDraw.indexOf(dot) + 1), dot.getBounds().centerX(), dot.getBounds().centerY(), textPaint);
        }

        mCustomProgressBar.drawProgressBar(canvas);
       // drawProgressBar(canvas);
//        for (Path p : pathsToReset) {
//            canvas.drawPath(p, mPaint);
//        }
//        for (Path p : pathsToStay) {
//            canvas.drawPath(p, mPaint);
//        }

    }
//    private void drawProgressBar(Canvas c) {
//        float barStartX = 20;
//        float barStartY = 20;
//        float barEndX;
//        float barEndY = 30;
//        barEndX = this.getWidth() - 20;
//        barPaint.setColor(Color.WHITE);
//        setCurrentSeekBarLength((barEndX - barStartX) * (ConnectDotsOneShotActivity.second / 30));
//        barPaint.setColor(Color.BLUE);
//        c.drawRect(barStartX, barStartY, getCurrentSeekBarLength(),
//                barEndY, barPaint);
//    }
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
        if (checkPath((int) x, (int) y)) {
            dotsToDraw.get(count).setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.DARKEN);
            count++;
            numberSpotted = true;
        }
        if (numberSpotted == true) {
            mPathStay.moveTo(startX, startY);
            mPathStay.lineTo(x, y);
            mPathStay = new Path();
            pathsToStay.add(mPathStay);
            numberSpotted = false;
            startX = x;
            startY = y;
        }
//        mCanvas.drawPath(mPathStay, mPaint);

    }

    private void touch_up(float xStop, float yStop) {
        mPath.reset();
        if (numberSpotted == true) {
            mPathStay.lineTo(xStop, yStop);
            mPathStay = new Path();
            pathsToStay.add(mPathStay);
            numberSpotted = false;
        }

//        mCanvas.drawPath(mPath, mPaint);
    }

    float startX = 0;
    float startY = 0;
    float stopX = 0;
    float stopY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(count==0){
                startX = dotsToDraw.get(0).getBounds().centerX();
                startY =  dotsToDraw.get(0).getBounds().centerY();}
                touch_start(startX, startY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                stopX = event.getX();
                stopY = event.getY();
                touch_up(stopX, stopY);
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }
    private void resetLevel() {

        TrailMakingActivity.timeToReset = true;

    }
    private List<ShapeDrawable> getDotList(Context context) {
        List<ShapeDrawable> dotList = new ArrayList<>();
        List<Point> uniquePointList;
        int diameter = 100;
        uniquePointList = createUniquePoints(context);

        for (Point p : uniquePointList) {
            dotList.add(createDot(p.x, p.y, diameter));
        }
        Collections.shuffle(dotList);
        return dotList;
    }

    private List<Point> createUniquePoints(Context context) {

        List<Point> pointList = new ArrayList<>();
        int width;
        int height;
        WindowManager wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();


        for (int i = 0; i < 8; i++) {
            Point mPoint = new Point();
            display.getSize(mPoint);
            width = (mPoint.x / 2) + randInt(-mPoint.x / 3, mPoint.x / 3);
            height = (i + 1) * (mPoint.y / 8) - randInt(mPoint.y / 14, mPoint.y / 8);

            mPoint.set(width, height);
            pointList.add(mPoint);
        }

        return pointList;
    }

    private ShapeDrawable createDot(int width, int height, int diameter) {
        ShapeDrawable dot = new ShapeDrawable(new OvalShape());
        dot.setBounds(width, height, width + diameter, height + diameter);
        dot.getPaint().setColor(0xff74AC23);
        return dot;
    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private boolean checkPath(int x, int y) {
        boolean correct = false;
        PathMeasure pm = new PathMeasure();

        if (count < 8) {
            if (Math.abs(x - dotsToDraw.get(count).getBounds().centerX()) < 50 && Math.abs(y - dotsToDraw.get(count).getBounds().centerY()) < 50) {
                correct = true;
            }
            return correct;
        } else {resetLevel();return false;}
    }


}
