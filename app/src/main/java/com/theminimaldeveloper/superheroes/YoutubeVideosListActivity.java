package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeVideosListActivity extends AppCompatActivity {

    RecyclerView recycler_character_videos;
    RequestQueue requestQueue;
    String YOUTUBE_API_KEY;
    ArrayList<ModelVideoDetails> videoDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_videos_list);

        recycler_character_videos = findViewById(R.id.recycler_character_videos);



        YOUTUBE_API_KEY = getString(R.string.youtube_api_key);
        String characterName = getIntent().getStringExtra("characterName");
        characterName = characterName.replaceAll("\\s", "%20");

        String baseUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&q=";
        String endUrl = "%20movie%20scene&type=video&key="+YOUTUBE_API_KEY;
        String finalUrl = baseUrl + characterName + endUrl;




        requestQueue = Volley.newRequestQueue(this);
        Log.d("dataa", "i am here");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, finalUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("dataa", "iiiiiiiii");
                videoDetailsList = new ArrayList<>();


                try {

                    Log.d("dataa", response.toString());
                    JSONArray array1 = response.getJSONArray("items");

                    for(int i=0 ; i<array1.length() ; i++){

                        JSONObject object1 = array1.getJSONObject(i);
                        String videoID = object1.getJSONObject("id").getString("videoId");
                        Log.d("dataaaaaaaaa id  ", videoID);

                        JSONObject snippetObject = object1.getJSONObject("snippet");
                        String title = snippetObject.getString("title");
                        Log.d("dataaaaaaaaa   tit", title);
                        String channelName = snippetObject.getString("channelTitle");
                        Log.d("dataaaaaaaaa   chn", channelName);

                        String thumbnailUrl = snippetObject.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                        Log.d("dataaaaaaaaa   thumb", thumbnailUrl);
                        videoDetailsList.add(new ModelVideoDetails(videoID, title, channelName, thumbnailUrl));

                        Log.d("dataaaaaaaaa   *******", videoDetailsList.get(i).getChannelName());


                    }

                    recycler_character_videos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycler_character_videos.setAdapter(new AdapterCharacterVideosList(videoDetailsList, YoutubeVideosListActivity.this));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("dataa", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YoutubeVideosListActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                Log.d("dataa", error.getMessage());
            }
        });


        requestQueue.add(request);

    }

    public void fetchData(String url){


    }

}