package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CharacterDetails extends AppCompatActivity {

    String description;

    TextView txt_description;
    ImageView img_character_icon;
    CardView card_comics, card_series, card_events, card_stories, card_marvel;
    JSONObject characterDetailsObject;

    int[] character_icon_ids = {
            R.drawable.black_panther_icon, R.drawable.black_widow_icon, R.drawable.bucky_icon, R.drawable.captain_america_icon, R.drawable.captain_marvel_icon,
            R.drawable.deadpool_icon, R.drawable.drax_icon, R.drawable.falcon_icon, R.drawable.groot_icon, R.drawable.hawkeye_icon,
            R.drawable.hulk_icon, R.drawable.iron_man_icon, R.drawable.korg_icon, R.drawable.loki_icon, R.drawable.mantic_icon,
            R.drawable.nebula_icon, R.drawable.nick_fury_icon, R.drawable.okoye_icon, R.drawable.rocket_racoon_icon,
            R.drawable.scarlet_witch_icon, R.drawable.spider_man_icon, R.drawable.thanos_icon, R.drawable.thor_icon,
            R.drawable.vision_icon, R.drawable.wasp_icon, R.drawable.wolverine_icon
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        txt_description = findViewById(R.id.txt_description);
        card_comics = findViewById(R.id.card_comics);
        card_series = findViewById(R.id.card_series);
        card_events = findViewById(R.id.card_events);
        card_stories = findViewById(R.id.card_stories);
        card_marvel = findViewById(R.id.card_marvel);
        img_character_icon = findViewById(R.id.img_character_icon);

        final String characterName = getIntent().getStringExtra("characterName");
        final int position = getIntent().getIntExtra("position", 0);

        img_character_icon.setImageResource(character_icon_ids[position]);


        String timeStamp = new Timestamp(System.currentTimeMillis()) + "";
        timeStamp = timeStamp.replaceAll("\\s", "");


        //Use your own Marvel private API key here.
        String MARVEL_PRIVATE_API_KEY = getString(R.string.marvel_private_api_key);

        //Use your own Marvel public API key here.
        String MARVEL_PUBLIC_API_KEY = getString(R.string.marvel_public_api_key);

        String FinalAPIHashingString = timeStamp+MARVEL_PRIVATE_API_KEY+MARVEL_PUBLIC_API_KEY;

        String hashedValue = GenerateMD5Hash.digest(FinalAPIHashingString);

        String requestUrl = "https://gateway.marvel.com/v1/public/characters?name="+characterName+"&ts="+timeStamp+"&apikey=80c88aff78e28137014a153aa169aa7d&hash="+hashedValue;
        requestUrl=requestUrl.replaceAll("\\s", "%20");

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject object1 = response.getJSONObject("data");
                    JSONArray array1 = object1.getJSONArray("results");
                    characterDetailsObject = array1.getJSONObject(0);

                    description = characterDetailsObject.getString("description");
                    if(description.isEmpty()){
                        txt_description.setText(characterName);
                    }else{
                        txt_description.setText(description);
                    }

                    JSONArray array2 = characterDetailsObject.getJSONArray("urls");
                    JSONObject object3 = array2.getJSONObject(0);
                    final String chatacter_url = object3.getString("url");


                    card_marvel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chatacter_url));
                            startActivity(browserIntent);
                        }
                    });


                    card_comics.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            getData("comics");

                        }
                    });

                    card_series.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            getData("series");
                        }
                    });


                    card_stories.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            getData("stories");

                        }
                    });

                    card_events.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            getData("events");

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CharacterDetails.this, "Network Error.", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request);

    }

    public void getData(String typeOfData){


        try {
            JSONObject object4 = characterDetailsObject.getJSONObject(typeOfData);
            JSONArray array3 = object4.getJSONArray("items");

            ArrayList<String> ItemsList= new ArrayList<>();

            for (int i=0; i<array3.length(); i++){

                JSONObject object = array3.getJSONObject(i);
                ItemsList.add(object.getString("name"));

            }

            Intent intent = new Intent(CharacterDetails.this, DisplayRecyclerView.class);
            intent.putExtra("ItemsList", ItemsList);
            startActivity(intent);




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}