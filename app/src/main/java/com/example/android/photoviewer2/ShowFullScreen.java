package com.example.android.photoviewer2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ShowFullScreen extends AppCompatActivity {

    private ImageView mImage;
    ViewPager viewPager;
    CustomSwipeAdapter csa;
    int picIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_screen);

        viewPager = (ViewPager) findViewById(R.id.view_pager);



//        mImage = (ImageView) findViewById(R.id.full_screen);
        Intent acceptIntent = getIntent();
        if (acceptIntent.hasExtra("lbj")){
            picIndex = acceptIntent.getIntExtra("lbj",-1)+1;
//            String tmp = "";
//            if (picIndex<10){
//               tmp = "@drawable/image0" + picIndex;
//            }
//            else tmp = "@drawable/image" + picIndex;
//
//            int imageResource = getResources().getIdentifier(tmp, null, getPackageName());
//            Drawable res = getResources().getDrawable(imageResource);
//            mImage.setImageDrawable(res);
        }
        csa = new CustomSwipeAdapter(this,picIndex);
        viewPager.setAdapter(csa);

    }
}
