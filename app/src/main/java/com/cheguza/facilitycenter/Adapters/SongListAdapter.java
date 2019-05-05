package com.cheguza.facilitycenter.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheguza.facilitycenter.Listeners.MyClickListener;
import com.cheguza.facilitycenter.R;

import java.util.ArrayList;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.MySongViewHolder> {

    Context context;
    ArrayList<SongModel> arrayListSongs;
    private MyClickListener onCardClickListener=null;
    SetOnLongPressedListener onCardLongPressedListener=null;

    public SongListAdapter(SetOnLongPressedListener onCardLongPressedListener) {
        this.onCardLongPressedListener = onCardLongPressedListener;
    }

    public SongListAdapter(MyClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    public void setOnCardLongPressedListener(SetOnLongPressedListener mLongListener)
    {
        onCardLongPressedListener=mLongListener;
    }
    public void setOnItemClickListener(MyClickListener mListener)
    {
        onCardClickListener=mListener;
    }
    public SongListAdapter(Context context, ArrayList<SongModel> arrayListSongs) {
        this.context = context;
        this.arrayListSongs = arrayListSongs;
    }

    @NonNull
    @Override
    public MySongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.songs_list_layout,viewGroup,false);
        MySongViewHolder holder=new MySongViewHolder(view,onCardClickListener,onCardLongPressedListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MySongViewHolder mySongViewHolder,int i) {
        String s=arrayListSongs.get(i).getmSongSize();
        float size=Integer.parseInt(s);
        size=(size/(1024*1024));
        String mSongSize=String.format("%.2f",size);

        mySongViewHolder.mSizetextView.setText(mSongSize+" MB");
        mySongViewHolder.textView.setText(arrayListSongs.get(i).getmSongText());
    }

    @Override
    public int getItemCount() {
        return arrayListSongs.size();
    }

    public static class MySongViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        TextView mSizetextView;
        LinearLayout songLinearlayout;
        public MySongViewHolder(@NonNull View itemView,
                                final MyClickListener listener,
                                final SetOnLongPressedListener mLongListener) {
            super(itemView);
            textView=itemView.findViewById(R.id.song_name);
            mSizetextView=itemView.findViewById(R.id.song_size_text);
            songLinearlayout=itemView.findViewById(R.id.linear_songlist_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.itemClicked(v,position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(mLongListener!=null)
                    {
                        int longPosition=getAdapterPosition();
                        mLongListener.onItemPressed(longPosition);
                    }
                    return true;
                }
            });
        }
    }
    public interface SetOnLongPressedListener{
        public void onItemPressed(int position);
    }
}
