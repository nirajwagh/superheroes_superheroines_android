package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.Timestamp;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String timeStamp = new Timestamp(System.currentTimeMillis()) + "";
        Toast.makeText(this, timeStamp, Toast.LENGTH_SHORT).show();
    }

    public void charactersClicked(View view) {

        startActivity(new Intent(HomeActivity.this, CharactersActivity.class));

    }

    public void youtubeVideosClicked(View view) {

        startActivity(new Intent(HomeActivity.this, CharactersActivity.class));

    }
}