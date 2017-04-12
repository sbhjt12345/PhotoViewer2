package com.example.android.photoviewer2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ChildActivity extends AppCompatActivity {
    String[] dates = {
       "2016-10-22", "2016-11-22", "2016-12-22",
       "2017-1-22","2017-2-22","2017-3-22"
    };

    String[] location = {
        "294 Kiyomizu 1-chome, Higashiyama Ward, Kyoto, Kyoto Prefecture 605-0862, Japan",
        "4 Chome-2-8 Shibakoen, Minato, Tokyo 105-0011, Japan",
        "3 Chome-19-14 Zōshigaya, Toshima-ku, Tōkyō-to 171-0032, Japan",
            "6 Chome-11-1 Roppongi, Minato-ku, Tōkyō-to 106-6108, Japan",
            "2 Chome-5-1 Kamiyoshida, Fujiyoshida-shi, Yamanashi-ken 403-0005, Japan",
            "Harajuku Station, 1 Chome-18 Jingūmae, Shibuya-ku, Tōkyō-to 150-0001, Japan"
    };

    String[] description = {
      "Last time when we visited Kyoto",
            "Yozakura amazed me so much",
            "Yet we visited another great sakura place",
            "Sadly it seems raining outside and I didnt bring my umbrella",
            "A great view of Mount Fuji",
            "Harajuku station is filled with swahggy people"
    };



    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mDisplay = (TextView) findViewById(R.id.tv_display);
        Intent intentFromMain = getIntent();
        if (intentFromMain.hasExtra(Intent.EXTRA_TEXT)){
            int picIndex = intentFromMain.getIntExtra(Intent.EXTRA_TEXT,-1);
            mDisplay.setText(Integer.toString(picIndex));
        }
    }
}
