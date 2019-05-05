package com.cheguza.facilitycenter.Adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cheguza.facilitycenter.Listeners.MyClickListener;
import com.cheguza.facilitycenter.Listeners.OnVideoClickListener;
import com.cheguza.facilitycenter.R;
import com.cheguza.facilitycenter.VideoPlayer.PlayVideoView;

import java.io.File;
import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoModel> arrayListVideos;
    Activity activity;


    public VideoListAdapter(Context context, ArrayList<VideoModel> arrayListVideos,Activity activity) {
        this.context = context;
        this.arrayListVideos = arrayListVideos;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_list_layout,viewGroup,false);

        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    public void deleteItem(File path, int position)
    {
        path.delete();
        arrayListVideos.remove(position);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        TextView textView=viewHolder.textView;

        String s=arrayListVideos.get(i).getmSize();
        float size=Integer.parseInt(s);
        size=(size/(1024*1024));
        String mVideoSize=String.format("%.2f",size);
        Glide.with(context).load("file://"+ arrayListVideos.get(i).getmThumb())
                .circleCrop()
                .skipMemoryCache(false)
                .into(viewHolder.imageView);
        textView.setText(arrayListVideos.get(i).getmVideoText());
        viewHolder.sizetextView.setText(mVideoSize+" MB");
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PlayVideoView.class);
                intent.putExtra("video",arrayListVideos.get(i).getmPath());
                activity.startActivity(intent);
            }
        });
        viewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"One Tap Is Sufficient to play Video",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListVideos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView sizetextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.thumb_image);
            textView=itemView.findViewById(R.id.video_name);
            sizetextView=itemView.findViewById(R.id.video_size_text);
        }
    }

}