package com.example.android.popularmovies.utilities;

import android.util.Log;

import com.example.android.popularmovies.adapter.MovieItem;
import com.example.android.popularmovies.data.AppStaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvaro.carvalho on 21/06/17.
 */

public class MoviesJsonUtils {

    /**
     * Converts an json array os movies to an object list of movies known by the adapter.
     * @param jsonMovieArray
     * @return List of MovieItem objects for the adapter.
     */
    public static List<MovieItem> getMovieItemsFormJsonArray(JSONArray jsonMovieArray){
        ArrayList<MovieItem> movieList = new ArrayList<MovieItem>();

        try{

            for (int i = 0; i < jsonMovieArray.length(); i++) {
                JSONObject jo = jsonMovieArray.getJSONObject(i);
                MovieItem movieItem = new MovieItem(jo.getString(
                    AppStaticData.MOVIEDB_RESPONSE_ORIGINAL_TITLE_NAME),
                    jo.getString(AppStaticData.MOVIEDB_RESPONSE_POSTER_PATH_NAME),
                    AppStaticData.getW185PathImage());
                movieList.add(movieItem);
            }

            return movieList;
        } catch(JSONException e){
            Log.d("Error parsing JSON. ", e.getMessage());
            return null;
        }

    }
}
