package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
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
    LayoutInflater inflater;
    View v;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
        count = 0;
        mBitmaps = new ArrayList<>();
        Log.i("Adapter", " Construct ");
//        inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = new View(mContext);
//        v = inflater.inflate(R.layout.answer_images, null);
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

        ImageView imageView;
//        if (convertView == null) {
        // if it's not recycled, initialize some attributes
        imageView = new ImageView(mContext);
        imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);
//        } else {
       // imageView = (ImageView) convertView;
//        }


        imageView.setImageBitmap(mBitmaps.get(position));
        Log.i("Adapter", " if  " + position);
        return imageView;


//            inflater = (LayoutInflater) mContext
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = new View(mContext);
//            v = inflater.inflate(R.layout.answer_images, null);
//           ImageView imageView = (ImageView) v.findViewById(R.id.grid_image);
//
//
//            mIvArray.get(count).buildDrawingCache(true);
//            Bitmap b = Bitmap.createBitmap(mIvArray.get(mIvArray.).getDrawingCache(true));
//            imageView.setImageBitmap(b);
//            // mIvArray.get(position).setDrawingCacheEnabled(false);
//
//            Log.i("Adapter", " if  " + position);
//        return v;
    }


    public static void addImage(Bitmap bm) {

        mBitmaps.add(bm);
        Log.i("Adapter", " Add image  " + mBitmaps.size());

    }
}
