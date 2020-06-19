package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

//comment/
public class MainActivity extends AppCompatActivity {
//Daniela's comment
    TextView GameName;
    TextView GameDescription;
    ImageView ConfusedCatimg;
    Button GameMode;
   // ImageView Logo1;
    private ArrayList<JSONObject>charactersrm;
    HashSet<String> wantedChars;

    private String URL="https://rickandmortyapi.com/api/character/";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameName=findViewById(R.id.GameName);
        GameDescription=findViewById(R.id.GameDescript);
        ConfusedCatimg=findViewById(R.id.confusedCat);
        GameMode=findViewById(R.id.GameMode);
        wantedChars= new HashSet<String>();
        charactersrm= new ArrayList<>();

        wantedChars.add("morty smith");
        wantedChars.add("summer smith");
        wantedChars.add("jerry smith");
        wantedChars.add("beth smith");
        wantedChars.add("rick sanchez");

       ConfusedCatimg.setImageResource(R.drawable.confusedcat);
        GameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToshows();
            }
        });



        requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d("request", "response:  "+ response.toString());
                        try {
                            JSONArray data  = response.getJSONArray("results");
                            Log.d("data",data.toString());

                            for (int i=0; i<data.length();i++){
                               String name= data.getJSONObject(i).getString("name").toLowerCase();
                                if (wantedChars.contains(name)){
                                    charactersrm.add(data.getJSONObject(i));
                                }
                            }
                          //  GameName.(charactersrm.get(0).getString("name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("response" , "Something went wrong");
                        }
                    }

);

requestQueue.add(jsonObjectRequest); //make the request


    }

    public void goToshows(){
        Intent toShows=new Intent(this,TvShowsActivity.class);
        startActivity(toShows);
    }
}
