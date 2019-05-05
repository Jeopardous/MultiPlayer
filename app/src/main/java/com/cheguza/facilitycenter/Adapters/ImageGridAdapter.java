package com.cheguza.facilitycenter.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cheguza.facilitycenter.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.MyImageViewHolder> {

    Context context;
    ArrayList<ImageModel> arrayListImages;

    public ImageGridAdapter(Context context, ArrayList<ImageModel> arrayListImages) {
        this.context = context;
        this.arrayListImages = arrayListImages;
    }

    @NonNull
    @Override
    public MyImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.image_grid_layout,viewGroup,false);
        MyImageViewHolder holder=new MyImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyImageViewHolder myImageViewHolder, int i) {

        Glide.with(context).load("file://"+arrayListImages.get(i).getmImageThumb())
                .centerCrop()
                .into(myImageViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayListImages.size();
    }

    public class MyImageViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.photo_Imageview);

        }
    }
}
