package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 2015/4/16.
 */
public class TextAdapter extends BaseAdapter {
    private Context mContext;

    int count;
    static List<TextView> mylist;


    // Constructor
    public TextAdapter(Context c) {
        mContext = c;
        count = 0;
        mylist = new ArrayList<>();
        Log.i("Adapter", " Construct ");
        fillArray();

    }

    private void fillArray (){
        TextView temp = new TextView(mContext);
        for (int i=0; i<47; i++){

            temp.setText("L");
            mylist.add(temp);

        }

        temp.setText("T");
        mylist.add(temp);
        Collections.shuffle(mylist);
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



        mylist.get(position).setLayoutParams(new GridView.LayoutParams(50, 50));
        mylist.get(position).setPadding(8, 8, 8, 8);

        Log.i("Adapter", " if  " + position);
        return  mylist.get(position);

    }



}
