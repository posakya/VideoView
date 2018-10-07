package com.creatu.videoplay;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

public class MainActivity extends AppCompatActivity  {

    private static final String TEST_URL = "http://pdsmarteducation.com/uploads/videos/1.1%20Management.mp4";

    private VideoView player;
    ProgressBar progressBar;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = findViewById(R.id.videoView);
        progressBar = findViewById(R.id.progressBar);

        mediaController= new MediaController(this);

       // player.getRotation();



        play();



    }

    public void play(){
        Uri uri = Uri.parse(TEST_URL);

        player.setVideoURI(uri);

        player.setMediaController(mediaController);

        mediaController.setAnchorView(player);


        player.start();


        progressBar.setVisibility(View.VISIBLE);

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();

                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        switch (what) {
                            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {
                                progressBar.setVisibility(View.GONE);
                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
                                progressBar.setVisibility(View.VISIBLE);
                                return true;
                            }
                            case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
                                progressBar.setVisibility(View.GONE);
                                return true;
                            }
                        }
                        return false;
                    }
                });
//                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
//                        progressBar.setVisibility(View.GONE);
//                        mediaPlayer.start();
//                    }
//                });


            }
        });



    }

}
