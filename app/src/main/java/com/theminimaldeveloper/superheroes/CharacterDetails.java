package com.theminimaldeveloper.superheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class CharacterDetails extends AppCompatActivity {

    String description;
    TextView txt_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        txt_description = findViewById(R.id.txt_description);

        final String characterName = getIntent().getStringExtra("characterName");


        String timeStamp = new Timestamp(System.currentTimeMillis()) + "";
        timeStamp = timeStamp.replaceAll("\\s", "");


        String MARVEL_PRIVATE_API_KEY = getString(R.string.marvel_private_api_key);
        String MARVEL_PUBLIC_API_KEY = getString(R.string.marvel_public_api_key);

        String FinalAPIHashingString = timeStamp+MARVEL_PRIVATE_API_KEY+MARVEL_PUBLIC_API_KEY;

        String hashedValue = GenerateMD5Hash.digest(FinalAPIHashingString);

        String requestUrl = "https://gateway.marvel.com/v1/public/characters?name="+characterName+"&ts="+timeStamp+"&apikey=80c88aff78e28137014a153aa169aa7d&hash="+hashedValue;
        requestUrl=requestUrl.replaceAll("\\s", "%20");

        Log.d("hashrequest", requestUrl);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject object1 = response.getJSONObject("data");
                    JSONArray array1 = object1.getJSONArray("results");
                    JSONObject object2 = array1.getJSONObject(0);

                    description = object2.getString("description");
                    if(description.isEmpty()){
                        txt_description.setText(characterName);
                    }else{
                        txt_description.setText(description);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CharacterDetails.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request);

    }

}