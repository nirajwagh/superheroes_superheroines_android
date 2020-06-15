package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

public class VideoPlayerActivity extends AppCompatActivity {

    YouTubePlayerView youtube_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        youtube_player = findViewById(R.id.youtube_player);
        final String videoId = getIntent().getStringExtra("videoId");

        getLifecycle().addObserver(youtube_player);
        youtube_player.enterFullScreen();

        youtube_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(videoId ,0);

            }
        });
    }
}