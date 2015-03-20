package canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.performance.cognitive.datagathering.BubbleActivity;

public class Bubble extends View {
    private int diameter;
    private int moveX;
    private int moveY;
    private int cCenterX;
    private int cCenterY;
    private ShapeDrawable bubble;
    private ShapeDrawable outerCircle;
    Point mPoint;
    private int diameterOuter;
    private CustomProgressBar mCustomProgressBar;


    Paint textPaint;

    public Bubble(Context context) {
        super(context);
        WindowManager wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        setmPoint(new Point());
        display.getSize(getmPoint());
        setcCenterX((getmPoint().x) / 2);
        setcCenterY((getmPoint().y) / 2);
        setMoveX((getmPoint().x) / 2);
        setMoveY((getmPoint().y) / 2);
        setScore(0f);
        setDiameter(150);
        setDiameterOuter(325);
        setTextPaint(new Paint());
        setBubble(new ShapeDrawable(new OvalShape()));
        setOuterCircle(new ShapeDrawable(new OvalShape()));
        getBubble().setBounds(getcCenterX() - getDiameter(), getcCenterY() - getDiameter(), getcCenterX() + getDiameter(), getcCenterY() + getDiameter());
        getOuterCircle().setBounds(getcCenterX() - getDiameterOuter(), getcCenterY() - getDiameterOuter(), getcCenterX() + getDiameterOuter(), getcCenterY() + getDiameterOuter());
        getBubble().getPaint().setColor(0xff74AC23);
        getOuterCircle().getPaint().setColor(Color.RED);
        getOuterCircle().getPaint().setStrokeWidth(50f);
        getOuterCircle().getPaint().setStyle(Paint.Style.STROKE);
        mCustomProgressBar=new CustomProgressBar(20,20,350,40);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCustomProgressBar.drawProgressBar(canvas, (float) BubbleActivity.sec);
        getBubble().draw(canvas);
        getOuterCircle().draw(canvas);
        canvas.drawText(Integer.toString((int)getScore()),mPoint.x/2 ,mPoint.y/8 , getTextPaint());
        updateScore(getBubble().getBounds().centerX(),getBubble().getBounds().centerY(), getOuterCircle().getBounds().centerX(),getOuterCircle().getBounds().centerY());
//        Log.i("Bubble", "     " + getBubble().getBounds().centerX()+"     " +getBubble().getBounds().centerY()+"     " + getcCenterX()+"     " +getcCenterY());
       Log.i("Bubble", "" + getScore());
    }

    public void move(float f, float g) {
        setMoveX((int) (getMoveX()+f));
        setMoveY((int) (getMoveY()+g));
        bubble.setBounds(getMoveX()-getDiameter(), getMoveY()-getDiameter(), getMoveX() + getDiameter(), getMoveY() + getDiameter());
    }

    public void updateScore(int cx,int cy, int ccx,int ccy){
        double distance = Math.sqrt((double)(Math.abs(cx- ccx)^2)+(double)(Math.abs(cy- ccy)^2));

//        Log.i("Bubble", "" + distance);


            if (distance<5){
                setScore(getScore()+100);
            }else if(distance<7){
                setScore(getScore()+50);

            }else if(distance < 10){
                setScore(getScore()-25);

            }else if(distance < 15) {
                setScore(getScore() - 50);
            }else if(distance < 20) {
                setScore(getScore() - 100);
            }
        // out of bounds
         }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public ShapeDrawable getBubble() {
        return bubble;
    }

    public void setBubble(ShapeDrawable bubble) {
        this.bubble = bubble;
    }

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }

    public int getcCenterX() {
        return cCenterX;
    }

    public void setcCenterX(int cCenterX) {
        this.cCenterX = cCenterX;
    }

    public int getcCenterY() {
        return cCenterY;
    }

    public void setcCenterY(int cCenterY) {
        this.cCenterY = cCenterY;
    }
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    private float score;

    public ShapeDrawable getOuterCircle() {
        return outerCircle;
    }

    public void setOuterCircle(ShapeDrawable outerCircle) {
        this.outerCircle = outerCircle;
    }

    public int getDiameterOuter() {
        return diameterOuter;
    }

    public void setDiameterOuter(int diameterOuter) {
        this.diameterOuter = diameterOuter;
    }
    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setTextSize(100f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        this.textPaint = paint;
    }

}