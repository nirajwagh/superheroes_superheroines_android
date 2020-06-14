package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class YoutubeCharacterListActivity extends AppCompatActivity {

    int[] character_icon_ids = {
            R.drawable.black_panther_icon, R.drawable.black_widow_icon, R.drawable.bucky_icon, R.drawable.captain_america_icon, R.drawable.captain_marvel_icon,
            R.drawable.deadpool_icon, R.drawable.drax_icon, R.drawable.falcon_icon, R.drawable.groot_icon, R.drawable.hawkeye_icon,
            R.drawable.hulk_icon, R.drawable.iron_man_icon, R.drawable.korg_icon, R.drawable.loki_icon, R.drawable.mantic_icon,
            R.drawable.nebula_icon, R.drawable.nick_fury_icon, R.drawable.okoye_icon, R.drawable.rocket_racoon_icon,
            R.drawable.scarlet_witch_icon, R.drawable.spider_man_icon, R.drawable.thanos_icon, R.drawable.thor_icon,
            R.drawable.vision_icon, R.drawable.wasp_icon, R.drawable.wolverine_icon
    };

    String[] charNames = new String[]{
        "Black Panther", "Black Widow", "Bucky", "Captain America", "Captain Marvel (Carol Danvers)",
                "Deadpool", "Drax", "Falcon", "Groot", "Hawkeye", "Hulk", "Iron Man", "Korg",
                "Loki", "Mantis", "Nebula", "Nick Fury", "Okoye", "Rocket Raccoon",
                "Scarlet Witch", "Spider-Man", "Thanos", "Thor", "Vision", "Wasp", "Wolverine"
    };

    RecyclerView recycler_char_list_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_character_list);

        recycler_char_list_video = findViewById(R.id.recycler_char_list_video);

        recycler_char_list_video.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_char_list_video.setAdapter(new AdapterCharacterListVideo(this, character_icon_ids, charNames));


    }
}