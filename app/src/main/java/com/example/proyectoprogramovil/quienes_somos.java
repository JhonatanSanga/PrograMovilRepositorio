package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class quienes_somos extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quienes_somos);
        videoView=(VideoView)findViewById(R.id.video_view);
        String videoPath="android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        //videoView.start();
    }
}
