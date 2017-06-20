package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.adapter.MovieItem;
import com.example.android.popularmovies.adapter.MovieItemAdapter;
import com.example.android.popularmovies.data.AppStaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mMovieRecyclerView;
    private TextView mErrorMessage;
    private ProgressBar mLoadingIndicator;
    private MovieItemAdapter mMovieItemAdapter;

    RecyclerView.LayoutManager mRecyclerViewLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMovieItemAdapter = new MovieItemAdapter(this, getRawData());
        mRecyclerViewLayoutManager = new GridLayoutManager(this, 2);

        mMovieRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mMovieRecyclerView.setAdapter(mMovieItemAdapter);

    }

    //FIXME load from file for now
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("raw_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<MovieItem> getRawData(){
        ArrayList<MovieItem> movieList = new ArrayList<MovieItem>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonMovieArray = obj.getJSONArray("results");


            for (int i = 0; i < jsonMovieArray.length(); i++) {
                JSONObject jo = jsonMovieArray.getJSONObject(i);
                Log.d("Details-->", jo.getString("title"));
                MovieItem movieItem = new MovieItem(jo.getString("original_title"),
                        jo.getString("poster_path"), AppStaticData.getW185Image());
                movieList.add(movieItem);
            }
        } catch(JSONException e){
            Log.d("Error-->", e.getMessage());
        }
        return movieList;
    }
}
