package com.example.android.photoviewer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.PicItemClickListener {

    private final String[] imageTitles = {
            "In Kyoto",
            "Sakura",
            "Yet another Sakura",
            "Workplace",
            "Tokyo",
            "Harajuku"
    };

    private final Integer[] imageID = {
            R.drawable.image01,
            R.drawable.image02,
            R.drawable.image03,
            R.drawable.image04,
            R.drawable.image05,
            R.drawable.image06,
    };

    private MyAdapter myAdapter;
    private RecyclerView pictures;
    private Toast mToast;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictures = (RecyclerView) findViewById(R.id.imagegallery);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        pictures.setLayoutManager(layoutManager);
        pictures.setHasFixedSize(true);
        List<createList> acturalPics = makeList(imageTitles,imageID);
        myAdapter = new MyAdapter(getApplicationContext(),acturalPics,this);
        pictures.setAdapter(myAdapter);
    }

    public List<createList> makeList(String[] imageTitles, Integer[] imageID){
        List<createList> list = new ArrayList<>();
        for (int i=0;i<imageID.length;i++){
            createList newpic = new createList(imageTitles[i],imageID[i]);
            list.add(newpic);
        }
        return list;
    }

    @Override
    public void onPicItemClick(int clickItemIndex) {
        if (mToast != null){
            mToast.cancel();
        }
        String toastmessage = "#" + clickItemIndex + " is shown";
        mToast = Toast.makeText(this,toastmessage,Toast.LENGTH_LONG);
        mToast.show();
    }
}
