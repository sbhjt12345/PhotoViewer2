package com.example.android.photoviewer2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Jiantao on 4/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private PicItemClickListener mOnClickListener;
    public List<createList> picList;
    Context context;
    ImageView im;

    public interface PicItemClickListener{
        public void onPicItemClick(int clickItemIndex,ImageView image);
        public void onPicItemLongClick(int clickItemIndex);
    }

    public MyAdapter(Context context, List<createList> picList, PicItemClickListener clicker){
        this.context = context;
        this.picList = picList;
        this.mOnClickListener = clicker;
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
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView img;
        TextView title;

        public MyViewHolder(View view){
            super(view);

            img = (ImageView) view.findViewById(R.id.img);
            title = (TextView) view.findViewById(R.id.title);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            im = (ImageView) view.findViewById(R.id.img);
            mOnClickListener.onPicItemClick(clickedPosition,im);
        }

        @Override
        public boolean onLongClick(View view) {
            int clickedPosition = getAdapterPosition();

            mOnClickListener.onPicItemLongClick(clickedPosition);
            return true;
        }
    }



}
