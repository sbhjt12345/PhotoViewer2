package com.example.android.photoviewer2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.photoviewer2.R.id.img;

/**
 * Created by Jiantao on 4/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private PicItemClickListener mOnClickListener;
    public List<createList> picList;
    Context context;
    ImageView im;
    int[] favcount;
    boolean showFav;

    public interface PicItemClickListener{
        public void onPicItemClick(int clickItemIndex,ImageView image);
        public void onPicItemLongClick(int clickItemIndex);
        public void onButtonItemClick(int clickItemIndex,Button bt);
    }

    public MyAdapter(Context context, List<createList> picList, PicItemClickListener clicker,int[] favcount,boolean showFav){
        this.context = context;
        this.picList = picList;
        this.mOnClickListener = clicker;
        this.favcount = favcount;
        this.showFav = showFav;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layOutIDForpicList = R.layout.cell_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layOutIDForpicList,viewGroup,shouldAttachToParentImmediately);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(picList.get(position).getImgTitle());
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.img.setImageResource(picList.get(position).getImgID());
        if (showFav==false){
            if (favcount[position] %2==1) holder.bt.setText("❤");
        }
        else{
            holder.bt.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView img;
        TextView title;
        Button bt;

        public MyViewHolder(View view){
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            bt = (Button) view.findViewById(R.id.like_button);
            if (!showFav){
                img.setOnClickListener(this);
                bt.setOnClickListener(this);
                img.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            if (view.getId()==img.getId()){
                im = (ImageView) view.findViewById(R.id.img);
                mOnClickListener.onPicItemClick(clickedPosition,im);
                //mOnClickListener.onPicItemLongClick(clickedPosition);
            }
            else  {
                bt = (Button) view.findViewById(R.id.like_button);
                mOnClickListener.onButtonItemClick(clickedPosition,bt);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            //TextView title = (TextView) view.findViewById(R.id.title);

            int clickedPosition = getAdapterPosition();
            if (view.getId()==img.getId()){
                mOnClickListener.onPicItemLongClick(clickedPosition);
            }
            return true;
        }
    }
}
