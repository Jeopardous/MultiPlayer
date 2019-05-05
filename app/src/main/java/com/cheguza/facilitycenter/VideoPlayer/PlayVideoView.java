package com.cheguza.facilitycenter.VideoPlayer;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.cheguza.facilitycenter.R;

public class PlayVideoView extends AppCompatActivity {
    VideoView videoView;
    ImageView imageView;
    SeekBar seekBar;
    String video_url;
    Handler handler;
    boolean isPlay=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch_video_layout);

        videoView=findViewById(R.id.video_view);
        imageView=findViewById(R.id.togal_play_btn);
        seekBar=findViewById(R.id.seekBar);

        video_url= getIntent().getStringExtra("video");
        videoView.setVideoPath(video_url);
        handler=new Handler();
        videoView.start();

        isPlay=true;
        imageView.setImageResource(R.drawable.ic_pause);

        updateSeekBar();
    }

    private void updateSeekBar() {
        handler.postDelayed(updateTimetask,100);
    }
    public Runnable updateTimetask=new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());
            handler.postDelayed(this,100);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimetask);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    handler.removeCallbacks(updateTimetask);
                    videoView.seekTo(seekBar.getProgress());
                    updateSeekBar();
                }
            });
        }
    };

    public void togglePlay(View view)
    {
        if(isPlay)
        {
            videoView.pause();
            isPlay=false;
            imageView.setImageResource(R.drawable.ic_play);
        }
        else if(isPlay==false)
        {
            videoView.start();
            updateSeekBar();
            isPlay=true;
            imageView.setImageResource(R.drawable.ic_pause);
        }
    }
}
