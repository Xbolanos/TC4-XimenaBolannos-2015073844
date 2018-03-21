package com.example.ximena.tc4_ximenabolannos_2015073844;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ximena on 19/3/2018.
 */

public class GridViewAdapter  extends BaseAdapter
    {

        private JSONArray movies;
        private Context mContext;

    public GridViewAdapter(Context c, JSONArray movies) {

        super();

        this.movies = movies;

        this.mContext = (Context) c;
    }


        @Override
        public int getCount() {
            return movies.length();
        }

        @Override
        public Object getItem(int position) {
            try {
                return movies.getJSONObject(position);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        View view;
        LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {

            view= inflator.inflate(R.layout.grid_row, null );
        }
        else
        {
            view = convertView;
        }
            TextView name=view.findViewById(R.id.name);
            TextView stars=view.findViewById(R.id.stars);
            TextView metascore=view.findViewById(R.id.metascore);
            ImageView image=view.findViewById(R.id.imageView);
            ImageView star=view.findViewById(R.id.star);
            ImageView meta=view.findViewById(R.id.meta);

            try {

                name.setText(movies.getJSONObject(position).getString("name"));
                stars.setText(movies.getJSONObject(position).getString("stars"));
                metascore.setText(movies.getJSONObject(position).getString("metascore"));

                if(image!=null) {
                    Picasso.with(mContext).load(movies.getJSONObject(position).getString("image")).into(image);
                    meta.setImageResource(R.drawable.meta);
                    star.setImageResource(R.drawable.star);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return view;
    }

    protected Bitmap getImage(String url) {
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(url).openConnection().getInputStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

}

