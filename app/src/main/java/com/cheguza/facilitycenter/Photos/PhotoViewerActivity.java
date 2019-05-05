package com.cheguza.facilitycenter.Photos;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cheguza.facilitycenter.Adapters.ImageGridAdapter;
import com.cheguza.facilitycenter.Adapters.ImageModel;
import com.cheguza.facilitycenter.R;

import java.util.ArrayList;

public class PhotoViewerActivity extends AppCompatActivity {
    RecyclerView imageGridRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<ImageModel> arrayListImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        imageGridRecyclerView=findViewById(R.id.photo_grid_recyclerview);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        imageGridRecyclerView.setLayoutManager(layoutManager);

        arrayListImage=new ArrayList<>();

        displayImages();
    }

    private void displayImages() {

        Uri uri;
        Cursor cursor;
        int Context_thumb;

        uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Thumbnails.DATA,MediaStore.Images.Media._ID};
        String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        cursor = getApplicationContext().getContentResolver()
                .query(uri, projection, null, null, orderBy);
        Context_thumb=cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA);

        while (cursor.moveToNext())
        {
            ImageModel imageModel=new ImageModel();
            imageModel.setmImageThumb(cursor.getString(Context_thumb));
            arrayListImage.add(imageModel);
        }

        ImageGridAdapter adapter=new ImageGridAdapter(getApplicationContext(),arrayListImage);
        imageGridRecyclerView.setAdapter(adapter);

    }
}
