package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.adapter.MovieItem;
import com.example.android.popularmovies.adapter.MovieItemAdapter;
import com.example.android.popularmovies.data.AppStaticData;
import com.example.android.popularmovies.domain.MovieInput;
import com.example.android.popularmovies.utilities.MoviesJsonUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieItemAdapter.ListItemClickListener{

    // TODO: code the menu to switch filter
    // TODO: code pagination on scroll
    // TODO: remove assets/raw_data.json

    private static final String TAG = MovieItemAdapter.class.getSimpleName();

    RecyclerView mMovieRecyclerView;
    TextView mErrorMessage;
    ProgressBar mLoadingIndicator;
    MovieItemAdapter mMovieItemAdapter;
    RecyclerView.LayoutManager mRecyclerViewLayoutManager;

    // param variables to query for movies
    boolean mIsPopularMoviesList = true;
    int mCurrentPage = 1;

    JSONArray mJsonMovieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMovieItemAdapter = new MovieItemAdapter(this, new ArrayList<MovieItem>(), this);
        mRecyclerViewLayoutManager = new GridLayoutManager(this, 3);

        mMovieRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mMovieRecyclerView.setAdapter(mMovieItemAdapter);

        loadMoviesData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        try {
            JSONObject jsonItemClicked = (JSONObject) mJsonMovieArray.get(clickedItemIndex);

            Context context = this;

            Class destinationClass = DetailActivity.class;

            Intent startDetailActivityIntent = new Intent(context, destinationClass);

            startDetailActivityIntent.putExtra("jsonMovieItem", jsonItemClicked.toString());
            startActivity(startDetailActivityIntent);
        } catch (JSONException e) {
            // FIXME start error message
            Log.d(TAG, "Json error :"+e.getMessage());
        }

    }

    private void loadMoviesData(){
        showMoviesList();

        MovieInput input = new MovieInput(mCurrentPage, mIsPopularMoviesList);
        new FetchMoviesTask().execute(input);
    }

    /**
     * Show the list of movies if no error has occurred fetching the data.
     */
    private void showMoviesList(){
        mMovieRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage(){
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesTask extends AsyncTask<MovieInput, Void, JSONArray>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(MovieInput... params) {
            JSONArray jsonArray = new JSONArray();

            if(params.length == 0 ){
                return null;
            }

            MovieInput movieInputParams = params[0];
            URL requestMoviesUrl = NetworkUtils.buildUrl(movieInputParams.isPopularMovies, movieInputParams.page);

            try {
                String response = NetworkUtils.getResponseFromHttpUrl(requestMoviesUrl);

                if(response != null){

                    JSONObject obj = new JSONObject(response);

                    return obj.getJSONArray(AppStaticData.MOVIEDB_RESPONSE_LIST_NAME);
                }

            } catch (IOException e) {

                Log.d(TAG, "Error parsing URL data.");
            } catch (JSONException e) {

                Log.d(TAG, "Error parsing json result data.");
            }

            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray moviesJsonList) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (!moviesJsonList.isNull(0)) {
                showMoviesList();
                mJsonMovieArray = moviesJsonList;
                mMovieItemAdapter.setMovieItems(
                        MoviesJsonUtils.getMovieItemsFormJsonArray(moviesJsonList));
                mMovieItemAdapter.notifyDataSetChanged();
            } else {
                showErrorMessage();
            }
        }
    }

}
