package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class YoutubeVideosListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_videos_list);

        String characterName = getIntent().getStringExtra("characterName");

        Toast.makeText(this, characterName, Toast.LENGTH_SHORT).show();
    }
}