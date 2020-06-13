package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DisplayRecyclerView extends AppCompatActivity {

    RecyclerView comicsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_list);

        comicsRecycler = findViewById(R.id.comics_recycler);

        ArrayList<String> ItemsList = getIntent().getStringArrayListExtra("ItemsList");

        comicsRecycler.setLayoutManager(new LinearLayoutManager(this));
        comicsRecycler.setAdapter(new AdapterComicsRecycler(ItemsList));




    }
}