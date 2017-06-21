package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.adapter.MovieItem;
import com.example.android.popularmovies.adapter.MovieItemAdapter;
import com.example.android.popularmovies.data.AppStaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieItemAdapter.ListItemClickListener{

    // TODO: code the intent to the detail view
    // TODO: code network util to connect to the api
    // TODO: code the radio to switch filter

    RecyclerView mMovieRecyclerView;
    TextView mErrorMessage;
    ProgressBar mLoadingIndicator;
    MovieItemAdapter mMovieItemAdapter;

    // TODO remove both when intents are implemented
    private static final String TAG = MovieItemAdapter.class.getSimpleName();
    private Toast mToast;

    // TODO: remove when NetworkUtil is working
    List<MovieItem> tempListMovies = new ArrayList<>();

    RecyclerView.LayoutManager mRecyclerViewLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO remove
        tempListMovies = getRawData();

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMovieItemAdapter = new MovieItemAdapter(this, tempListMovies, this);
        mRecyclerViewLayoutManager = new GridLayoutManager(this, 3);

        mMovieRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mMovieRecyclerView.setAdapter(mMovieItemAdapter);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }


        Log.d(TAG, "RECEIVING CLICK");

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + tempListMovies.get(clickedItemIndex).originalName + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    // FIXME: load from local file, for now.
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

    // TODO: move this logic to an network utils.
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
