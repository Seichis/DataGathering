package adapters;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.performance.cognitive.datagathering.SelectiveActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by DELL on 2015/4/16.
 */
public class TextAdapter extends BaseAdapter {
    private Context mContext;

    int count;
    public static List<TextView> mylist;


    // Constructor
    public TextAdapter(Context c) {
        mContext = c;
        count = 0;
        mylist = new ArrayList<>(48);
        Log.i("Adapter", " Construct ");


    }

    public void fillArray() {


        for (int i = 0; i < 54; i++) {

            Random rand = new Random();

            int randomNum = rand.nextInt((360 - 0) + 1);
            TextView temp = new TextView(mContext);


            temp.setText("L".toString());

            Rect mRect=new Rect(temp.getBottom(),temp.getLeft(),temp.getTop(),temp.getRight());
 //           temp.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//
//            translationX=temp.getTranslationX();
//            translationY=temp.getTranslationY();
            temp.setBottom(0);
            temp.setTop(0);
            temp.setLeft(0);
            temp.setRight(0);



//
//            temp.setTranslationX(0f);
//            temp.setTranslationY(0f);

            temp.setRotation((float) randomNum);
//
//            temp.setTranslationX(translationX);
//            temp.setTranslationY(translationY);



            //temp.setPadding(8, 8, 8, 8);
            temp.setTextSize(30);
            temp.setBottom(mRect.bottom);
            temp.setTop(mRect.top);
            temp.setLeft(mRect.left);
            temp.setRight(mRect.right);
            temp.setMaxHeight(30);
            temp.setMaxWidth(30);
            if (i>52){
                temp.setText("T".toString());
                temp.setMaxHeight(200);
                temp.setMaxWidth(200);
                temp.setPadding(10, 10, 10, 10);
            }
            temp.setLayoutParams(new GridView.LayoutParams(100, 100));

            mylist.add(temp);
        }

        Collections.shuffle(mylist);

    }

    public void clearList(){
        mylist.clear();
    }

    public int getCount() {

        return mylist.size();

    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView temp;

        temp = mylist.get(position);

        if (temp.getText().equals("T")){
            SelectiveActivity.tPosition=position;
        }
        //temp.setVisibility(View.VISIBLE);

        Log.i("Adapter", " if  " + temp.getText());

        return temp;

    }


}
