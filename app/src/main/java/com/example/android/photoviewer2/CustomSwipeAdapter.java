package com.example.android.photoviewer2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiantao on 4/12/17.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    private int pos;
    private boolean isFav;
    private int[] favcount;
    List<int[]> newimages;
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

    public CustomSwipeAdapter(Context context, int pos,int[] favcount,boolean isFav){
        this.context = context;
        this.pos = pos;
        this.favcount = favcount;
        this.isFav = isFav;
        newimages = generateList(buildImageList(images,isFav,favcount));

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
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }

    public int[] buildImageList(int[] images,boolean isFav, int[] favcount){
        int sum = 0;
        if (isFav){          // get only favorite photos
            for (int i=0;i<favcount.length;i++){
                if (favcount[i]%2==1) sum++;
            }
        }
        else return images;
        int[] newimage = new int[sum];
        int count = 0;
        if (isFav){
            for (int i=0;i<favcount.length;i++){
                if (favcount[i]%2==1) {
                    newimage[count] = images[i];
                    count++;
                }
            }
            return newimage;
        }
        else return images;
    }

    public int[] swap(int[] images, int pos){
        int[] res = new int[images.length];
        int count = 0;
        for (int i=pos-1;i<images.length;i++){
            res[count] = images[i];
            count++;
        }
        for (int i=0;i<pos-1;i++){
            res[count] = images[i];
            count++;
        }
        return res;
    }

    public List<int[]> generateList(int[] images){
        List<int[]> list = new ArrayList<>();
        int n = images.length;
        for (int i=1;i<=n;i++){
            list.add(swap(images,i));
        }
        return list;
    }
}
