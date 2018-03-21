package com.example.ximena.tc4_ximenabolannos_2015073844;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    // URL to get contacts JSON
    private static String url = "http://www.imdb.com/list/ls064079588/";
    private GridViewAdapter mAdapter;
    private GridView grid;
    private JSONArray movies=new JSONArray();
    private Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c= getApplicationContext();
        getWebsite();


    }


    private void getWebsite() {
       final JSONArray jsonArray = new JSONArray();
        new Thread(new Runnable() {
            @Override
            public void run() {


                try {


                    Document doc = Jsoup.connect(url).get();
                    String title = doc.title();
                    Elements links = doc.getElementsByClass("lister list detail sub-list");
                    Elements names= links.select("h3");
                    Elements stars= doc.getElementsByClass("inline-block ratings-imdb-rating");
                    Elements metascores= doc.getElementsByClass("metascore  favorable");

                    Elements images= doc.getElementsByClass("loadlate");

                    for (int i=0; i<20; i++) {
                        JSONObject obj = new JSONObject();

                        try {
                            obj.put("name", names.get(i).text());
                            obj.put("stars", stars.get(i).attr("data-value"));
                            obj.put("metascore", metascores.get(i).text());
                            obj.put("image", images.get(i).attr("loadlate"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        movies.put(obj);
                        jsonArray.put(obj);


                    }

                } catch (IOException e) {

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("JSON ARRAY:", jsonArray.toString());

                        grid =  findViewById(R.id.gridView);

                        mAdapter = new GridViewAdapter(c, movies);

                        grid.setAdapter(mAdapter);


                    }

                });
            }

        }).start();



    }

}
