package com.cheguza.facilitycenter.VideoPlayer;

import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.cheguza.facilitycenter.Adapters.VideoListAdapter;
import com.cheguza.facilitycenter.Adapters.VideoModel;
import com.cheguza.facilitycenter.Listeners.OnVideoClickListener;
import com.cheguza.facilitycenter.R;

import java.io.File;
import java.util.ArrayList;

public class VideoPlayerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    VideoListAdapter adapter;

    ArrayList<VideoModel> arrayListVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        recyclerView = findViewById(R.id.video_list);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayListVideo = new ArrayList<>();

        fatchVideos();
    }

    private void fatchVideos() {
        Uri uri;
        Cursor cursor;
        int Column_index_data, Column_index_folder_name, thumb,Column_size;
        String videoSize=MediaStore.Video.Media.SIZE;

        final String path= MediaStore.MediaColumns.DATA;
        String absolutePathImage = null;
        String videoName = null;

        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media._ID,
                videoSize,
                MediaStore.Video.Thumbnails.DATA};

        String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        cursor = getApplicationContext().getContentResolver()
                .query(uri, projection, null, null, orderBy + " DESC");

        Column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        Column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
        thumb = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        Column_size=cursor.getColumnIndexOrThrow(videoSize);

        while (cursor.moveToNext()) {
            absolutePathImage = cursor.getString(Column_index_data);
            videoName = cursor.getString(Column_index_folder_name);
            VideoModel videoModel = new VideoModel();
            videoModel.setmSelected(false);
            videoModel.setmPath(absolutePathImage);
            videoModel.setmThumb(cursor.getString(thumb));
            videoModel.setmVideoText(videoName);
            videoModel.setmSize(cursor.getString(Column_size));

            arrayListVideo.add(videoModel);
        }

        adapter = new VideoListAdapter(getApplicationContext(),
                arrayListVideo,VideoPlayerActivity.this);
        recyclerView.setAdapter(adapter);
        int x=adapter.getItemCount();
        Toast.makeText(getApplicationContext(),"total item:"+x,Toast.LENGTH_SHORT).show();

        new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0,ItemTouchHelper.RIGHT |ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return true;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                adapter.deleteItem(new File(path),viewHolder.getAdapterPosition());

                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);
    }
}