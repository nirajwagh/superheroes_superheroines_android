package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void charactersClicked(View view) {

        startActivity(new Intent(HomeActivity.this, CharactersActivity.class));

    }

    public void youtubeVideosClicked(View view) {

        startActivity(new Intent(HomeActivity.this, YoutubeCharacterListActivity.class));

    }
}