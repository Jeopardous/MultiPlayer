package com.cheguza.facilitycenter.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheguza.facilitycenter.Home.CardViewDataSet;
import com.cheguza.facilitycenter.Home.ClickListener;
import com.cheguza.facilitycenter.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ClickListener clicklistener = null;
    private CardView cardView;


    private ArrayList<CardViewDataSet> cardViewDataSets;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.insideCardImageView);
            textView=itemView.findViewById(R.id.insideCardTextView);
            linearLayout=itemView.findViewById(R.id.cardviewLayout);

            cardView=itemView.findViewById(R.id.cardView);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clicklistener !=null){
                        clicklistener.itemClicked(v,getAdapterPosition());
                    }
                }
            });
        }

    }

    public MyAdapter(ArrayList<CardViewDataSet> cardViewDataSets) {
        this.cardViewDataSets = cardViewDataSets;
    }


    public void setClickListener(ClickListener clickListener){
        this.clicklistener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_cardview,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final  MyViewHolder holder, final int i) {
        TextView textView = holder.textView;
        ImageView imageView = holder.imageView;

        textView.setText(cardViewDataSets.get(i).getTextname());
        imageView.setImageResource(cardViewDataSets.get(i).getImage());

    }

    @Override
    public int getItemCount() {
        return cardViewDataSets.size();
    }
}

