package com.example.android.photoviewer2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    public boolean[] isFavorite = new boolean[10];

    private MyAdapter myAdapter;
    private RecyclerView pictures;
    private Toast mToast;
    boolean isImageFitToScreen;
    private static final String TAG = "MainActivity";




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
    public void onPicItemClick(int clickItemIndex, ImageView image) {
        //        if (mToast != null){
//            mToast.cancel();
//        }
//        String toastMessage = "Photo " + imageTitles[clickItemIndex] + " is added to Favorite "
//                + image;
//
//        String toastMessage2 = "Photo " + imageTitles[clickItemIndex] + " Removed from Favorite"
//                + image;
//
//        if (isFavorite[clickItemIndex]){
//            mToast = Toast.makeText(this,toastMessage2,Toast.LENGTH_LONG);
//            TextView v = (TextView) mToast.getView().findViewById(android.R.id.message);
//            v.setTextColor(Color.BLUE);
//            isFavorite[clickItemIndex] = false;
//        }
//        else{
//            mToast = Toast.makeText(this,toastMessage,Toast.LENGTH_LONG);
//            TextView v = (TextView) mToast.getView().findViewById(android.R.id.message);
//            v.setTextColor(Color.YELLOW);
//            isFavorite[clickItemIndex] = true;
//        }
//
//        mToast.show();
        Intent fullIntent = new Intent(MainActivity.this,ShowFullScreen.class);
        fullIntent.putExtra("lbj",clickItemIndex);
        startActivity(fullIntent);

    }

    @Override
    public void onPicItemLongClick(int clickItemIndex) {


        if (mToast != null){
            mToast.cancel();
        }
        String toastmessage = "#" + clickItemIndex + " is shown";
        mToast = Toast.makeText(this,toastmessage,Toast.LENGTH_LONG);
        mToast.show();

        Intent intent = new Intent(MainActivity.this,ChildActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT,clickItemIndex);
        startActivity(intent);




    }

    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }


}
