package com.example.android.photoviewer2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewSwitcher;


public class ChildActivity extends AppCompatActivity {
    String[] dates = {
       "2016-10-22", "2016-11-22", "2016-12-22",
       "2017-1-22","2017-2-22","2017-3-22"
    };

    String[] location = {
        "Kinkakuji Temple",
        "Shibakoen",
        "Kamakura",
            "Roppongi Hills",
            "Fuji Mountain",
            "Harajuku Station"
    };

    String[] description = {
      "Last time when we visited Kyoto",
            "Yozakura amazed me so much",
            "Yet we visited another great sakura place",
            "Sadly it seems raining outside and I didnt bring my umbrella",
            "A great view of Mount Fuji",
            "Harajuku station is filled with swaggy people"
    };



    private TextView mDisplay;
    private TextView mPlace;
    private TextView mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mDisplay = (TextView) findViewById(R.id.tv_display);
        mPlace = (TextView) findViewById(R.id.tv_place);
        mInfo = (TextView) findViewById(R.id.clickable_text_view);
        Intent intentFromMain = getIntent();
        if (intentFromMain.hasExtra(Intent.EXTRA_TEXT)){
            int picIndex = intentFromMain.getIntExtra(Intent.EXTRA_TEXT,-1);
            String date = "Date Shot : " + dates[picIndex];
            String place = "Place Shot : " + location[picIndex];
            String info = "Description : " + description[picIndex];
            mDisplay.setText(date);
            mPlace.setText(place);
            mInfo.setText(info);

        }
    }

    public void openMap(View view){
        Intent intentFromMain = getIntent();
        if (intentFromMain.hasExtra(Intent.EXTRA_TEXT)){
            int picIndex = intentFromMain.getIntExtra(Intent.EXTRA_TEXT,-1);
            String[] arr = location[picIndex].split(" ");
            StringBuffer sb = new StringBuffer();
            for (String c :arr){
                sb.append(c);
                sb.append("+");
            }
            int len = sb.length();


            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + sb.toString().substring(0,len)));
            startActivity(intent);

        }
    }

    public void switchText(View view){
        TextView tv = (TextView) findViewById(R.id.clickable_text_view);
        EditText et = (EditText) findViewById(R.id.hidden_edit_view);
        Button bt = (Button) findViewById(R.id.toggle);
        int isshown = tv.getVisibility();
        if (isshown==View.VISIBLE) {
            tv.setVisibility(View.INVISIBLE);
            et.setVisibility(View.VISIBLE);
            bt.setText("OK");
        }
        else{
            String ss = "Description : " + et.getText().toString();
            tv.setVisibility(View.VISIBLE);
            tv.setText(ss);
            bt.setText("Edit");
            et.setVisibility(View.INVISIBLE);
        }

    }

    public void shareOn(View view){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        Intent intentFromMain = getIntent();
        int picIndex = 0;
        if (intentFromMain.hasExtra(Intent.EXTRA_TEXT)){
            picIndex = intentFromMain.getIntExtra(Intent.EXTRA_TEXT,-1);

        }
        int c = 0;
        switch (picIndex) {
            case 0:
                c = R.drawable.image01;
                break;
            case 1:
                c = R.drawable.image02;
                break;
            case 2:
                c = R.drawable.image03;
                break;
            case 3:
                c = R.drawable.image04;
                break;
            case 4:
                c = R.drawable.image05;
                break;
            case 5:
                c = R.drawable.image06;
                break;
        }
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(c)
                + '/' + getResources().getResourceTypeName(c) + '/'
                + getResources().getResourceEntryName(c) );

        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, location[picIndex]);
        startActivity(Intent.createChooser(shareIntent, "send pic to..."));

    }
}
