package com.example.android.photoviewer2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.string.yes;

public class MainActivity extends AppCompatActivity implements MyAdapter.PicItemClickListener,SharedPreferences.OnSharedPreferenceChangeListener {

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

    public boolean[] fav = {true,true,true,true,true,true};
    public int[] favcount = new int[6];


    private MyAdapter myAdapter;
    private RecyclerView pictures;
    private Toast mToast;
    private static final String TAG = "Yo know whos talking to Papa";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictures = (RecyclerView) findViewById(R.id.imagegallery);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        pictures.setLayoutManager(layoutManager);
        pictures.setHasFixedSize(true);
        setupSharedPreferences();
        List<createList> acturalPics = makeList(imageTitles,imageID);
        myAdapter = new MyAdapter(getApplicationContext(),acturalPics,this,favcount,false);
        pictures.setAdapter(myAdapter);
    }

    private void setupSharedPreferences() {
        // Get all of the values from shared preferences to set it up
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Register the listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }



    public List<createList> makeList(String[] imageTitles, Integer[] imageID){
        List<createList> list = new ArrayList<>();
        for (int i=0;i<imageID.length;i++){
            Log.i("picture " + i,"value is: " + fav[i]);
            //if (fav[i]==true) {
                createList newpic = new createList(imageTitles[i], imageID[i]);
                list.add(newpic);
            //}
        }
        return list;
    }

    public List<createList> makeFavList(String[] imageTitles,Integer[] imageID, int[] favcount){
        List<createList> list = new ArrayList<>();
        for (int i=0;i<imageID.length;i++){
            if (favcount[i]%2 != 0){
                createList newpic = new createList(imageTitles[i], imageID[i]);
                list.add(newpic);
            }
        }
        return list;
    }

    @Override
    public void onPicItemClick(int clickItemIndex, ImageView image) {
        Log.i("im clicking","the short button");
        Intent fullIntent = new Intent(MainActivity.this,ShowFullScreen.class);
        fullIntent.putExtra("lbj",clickItemIndex);
        fullIntent.putExtra("favlist",favcount);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFav = sharedPreferences.getBoolean("show_favorite",false);
        //Log.i("we need another test",""+ isFav);
        fullIntent.putExtra("isfav",isFav);
        startActivity(fullIntent);

    }

    @Override
    public void onPicItemLongClick(int clickItemIndex) {
        //Log.i("im clicking",title.getText().toString());

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

    @Override
    public void onButtonItemClick(int clickItemIndex, Button bt) {
        Log.i("i clikcked","a button" + clickItemIndex);
        String nows = bt.getText().toString();
        if (nows=="❤"){
            bt.setText("○");
        }
        else {
            bt.setText("❤");
        }
        favcount[clickItemIndex]++;
    }

    public void fullScreen() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
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
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent startSettingsActivity = new Intent(this,SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
          if (key.equals("show_favorite")){
              boolean isFav = sharedPreferences.getBoolean("show_favorite",false);
              for (int i=0;i<favcount.length;i++){
                  Log.i("hello, we have reached " + i, " and this is " + favcount[i]);
              }
              if (isFav==false){
                  List<createList> acturalPics = makeList(imageTitles,imageID);
                  myAdapter = new MyAdapter(getApplicationContext(),acturalPics,this,favcount,isFav);
                  pictures.setAdapter(myAdapter);
              }
              else{
                  List<createList> acturalPics = makeFavList(imageTitles,imageID,favcount);
                  myAdapter = new MyAdapter(getApplicationContext(),acturalPics,this,favcount,isFav);
                  pictures.setAdapter(myAdapter);
              }
          }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
