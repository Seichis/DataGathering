package canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Bubble extends View {
    private int diameter;
    private int moveX;
    private int moveY;
    private int cCenterX;
    private int cCenterY;
    private ShapeDrawable bubble;
    Point mPoint;

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
        setDiameter(200);
        setBubble(new ShapeDrawable(new OvalShape()));
        getBubble().setBounds(getcCenterX()-getDiameter(), getcCenterY()-getDiameter(), getcCenterX() + getDiameter(), getcCenterY() + getDiameter());
        getBubble().getPaint().setColor(0xff74AC23);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        getBubble().draw(canvas);
        Log.i("Bubble", "" + getBubble().getBounds());
    }

    public void move(float f, float g) {
        setMoveX((int) (getMoveX()+f));
        setMoveY((int) (getMoveY()+g));
        bubble.setBounds(getMoveX()-getDiameter(), getMoveY()-getDiameter(), getMoveX() + getDiameter(), getMoveY() + getDiameter());
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
}