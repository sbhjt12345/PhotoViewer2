package com.example.android.photoviewer2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jiantao on 4/12/17.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    private int pos;
    private int[] images = {R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04,
                            R.drawable.image05,R.drawable.image06};
    private int[] images2 = {R.drawable.image02,R.drawable.image03,R.drawable.image04,
            R.drawable.image05,R.drawable.image06,R.drawable.image01};
    private int[] images3 = {R.drawable.image03,R.drawable.image04,
            R.drawable.image05,R.drawable.image06,R.drawable.image01,R.drawable.image02};
    private int[] images4 = {R.drawable.image04,
            R.drawable.image05,R.drawable.image06,R.drawable.image01,R.drawable.image02,R.drawable.image03};
    private int[] images5 = {R.drawable.image05,R.drawable.image06,R.drawable.image01,
            R.drawable.image02,R.drawable.image03,R.drawable.image04};
    private int[] images6 = {R.drawable.image06,R.drawable.image01,
            R.drawable.image02,R.drawable.image03,R.drawable.image04,R.drawable.image05};
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context context, int pos){
        this.context = context;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== (LinearLayout) object;
    }

    @Override
    public int getItemPosition(Object object) {
        return pos;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        int pos = getItemPosition(imageView);
        TextView textView = (TextView) item_view.findViewById(R.id.image_count);
        switch (pos){
            case 1 : {
                imageView.setImageResource(images[position]);
                break;
            }
            case 2 : {
                imageView.setImageResource(images2[position]);
                break;
            }
            case 3 : {
                imageView.setImageResource(images3[position]);
                break;
            }
            case 4 : {
                imageView.setImageResource(images4[position]);
                break;
            }
            case 5 : {
                imageView.setImageResource(images5[position]);
                break;
            }
            case 6 : {
                imageView.setImageResource(images6[position]);
                break;
            }


        }

       // imageView.setImageResource(images[pos]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }
}
