package com.example.ximena.tc4_ximenabolannos_2015073844;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
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
                //loadImageFromURL(movies.getJSONObject(position).getString("image"),image);
                if(image!=null) {
                    Picasso.with(mContext).load(movies.getJSONObject(position).getString("image")).into(image);
                    Picasso.with(mContext).load(R.drawable.meta).into(meta);
                    Picasso.with(mContext).load(R.drawable.star).into(star);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        return view;
    }

}

