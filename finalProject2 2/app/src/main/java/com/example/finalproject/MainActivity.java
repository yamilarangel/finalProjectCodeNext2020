package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

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


import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

//comment/
public class MainActivity extends AppCompatActivity {
//Daniela's comment
    TextView GameName;
    TextView GameDescription;
    ImageView GameLogo;
    Button TvShows;
    Button Movies;
    private ArrayList<Object>charactersrm;
    private String URL="https://rickandmortyapi.com/api/character/";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("request", "response:  "+ response );

                    }

                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("response" , "error: " + error);
                        }
                    }

);

requestQueue.add(jsonObjectRequest); //make the request




                    GameName=findViewById(R.id.GameName);
        GameDescription=findViewById(R.id.GameDescript);
        GameLogo=findViewById(R.id.GameLogo);
        TvShows=findViewById(R.id.shows);
        Movies=findViewById(R.id.movies);

        TvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // goToshows
            }
        });
    }
}
