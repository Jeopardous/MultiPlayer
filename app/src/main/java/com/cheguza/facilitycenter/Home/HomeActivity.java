package com.cheguza.facilitycenter.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cheguza.facilitycenter.Adapters.MyAdapter;
import com.cheguza.facilitycenter.MusicPlayer.MusicPlayerActivity;
import com.cheguza.facilitycenter.Photos.PhotoViewerActivity;
import com.cheguza.facilitycenter.R;
import com.cheguza.facilitycenter.SettingsActivity;
import com.cheguza.facilitycenter.UserActivity;
import com.cheguza.facilitycenter.VideoPlayer.VideoPlayerActivity;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements ClickListener {

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<CardViewDataSet> cardViewDataSets;
    ProgressBar progressBar;
    LinearLayout mCardLinearLayout;
    CardView mHomeCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeCard=findViewById(R.id.cardView);
        mCardLinearLayout=findViewById(R.id.inside_card_layout);
//======================================ToolBar SetUp=================================

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//=======================================Recycler View================================

        recyclerView=findViewById(R.id.recyclerView);


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cardViewDataSets=new ArrayList<CardViewDataSet>();
        for(int i = 0; i < CardViewDataDetails.textName.length; i++)
        {
            cardViewDataSets.add(new CardViewDataSet(CardViewDataDetails.textName[i],
                    CardViewDataDetails.images[i],
                    CardViewDataDetails.id[i]));
        }

        adapter=new MyAdapter(cardViewDataSets);
        ((MyAdapter) adapter).setClickListener(this);
        recyclerView.setAdapter(adapter);

//=====================================BottomNavigation View===========================


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Home:
                        Intent i1=new Intent(HomeActivity.this,HomeActivity.class);
                        startActivity(i1);
                        break;
                    case R.id.User:
                        Intent i2=new Intent(HomeActivity.this, UserActivity.class);
                        startActivity(i2);
                        break;
                    case R.id.settings:
                        Intent i3=new Intent(HomeActivity.this, SettingsActivity.class);
                        startActivity(i3);
                        break;
                }
                return true;
            }
        });

    }
    @Override
    public void itemClicked(View view, int position) {
        if(position==0)
        {
            Intent i1=new Intent(HomeActivity.this, MusicPlayerActivity.class);
            startActivity(i1);
        }
        else if(position==1)
        {
            Intent i1=new Intent(HomeActivity.this, VideoPlayerActivity.class);
            startActivity(i1);
        }
        else if(position==2)
        {
            Intent i1=new Intent(HomeActivity.this, PhotoViewerActivity.class);
            startActivity(i1);
        }
    }
}
