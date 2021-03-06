package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    int count;
    static List<Bitmap> mBitmaps;


    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        count = 0;
        mBitmaps = new ArrayList<>();
        Log.i("Adapter", " Construct ");

    }


    public int getCount() {

        return mBitmaps.size();

    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView bubble;

        bubble = new ImageView(mContext);
        bubble.setLayoutParams(new GridView.LayoutParams(100, 100));
        bubble.setScaleType(ImageView.ScaleType.CENTER_CROP);
        bubble.setPadding(8, 8, 8, 8);

        bubble.setImageBitmap(mBitmaps.get(position));
        Log.i("Adapter", " if  " + position);
        return bubble;

    }


    public static void addImage(Bitmap bm) {

        mBitmaps.add(bm);
        Log.i("Adapter", " Add image  " + mBitmaps.size());

    }
}
