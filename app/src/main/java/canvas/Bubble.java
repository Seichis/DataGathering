package canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Bubble extends View {
    private int diameter;
    private int x=0;
    private int y=0;
    private int width=0; private int height=0;
    private ShapeDrawable bubble;
    public Bubble(Context context) {
        super(context);
        createBubble(context);
    }
    private void createBubble(Context ctx) {
        WindowManager wm = (WindowManager)
                ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = (size.x)/2;
        height = (size.y)/2;
        x = width;
        y = height;
        diameter = 300;
        bubble = new ShapeDrawable(new OvalShape());
        bubble.setBounds(x, y, x + diameter, y + diameter);
        bubble.getPaint().setColor(0xff74AC23);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bubble.draw(canvas);
    }
    public void move(float f, float g) {
        x = (int) (x + f);
        y = (int) (y + g);
        bubble.setBounds(x, y, x + diameter, y + diameter);
    }


}