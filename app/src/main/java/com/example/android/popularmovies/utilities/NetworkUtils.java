package com.example.android.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.data.AppStaticData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by alvaro.carvalho on 21/06/17.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    /**
     *
     * @param imageApiBasePath base url path to the movie image API
     * @param jpegImageName string with the jpg movie name
     * @return the URI of an movie image on the <a href="http://themoviedb.org">themoviedb.org</a> API
     */
    public static Uri createImageUri(String imageApiBasePath, String jpegImageName){
        return Uri.parse(imageApiBasePath+jpegImageName);
    }

    /**
     *
     * @param isPopularList boolean indicating if is the list of popular or top rated movies.
     * @param page number of the page to query.
     * @return the complete URL to query for movies list.
     */
    public static URL buildUrl(boolean isPopularList, Integer page) {
        String movieListTypeBase = AppStaticData.MOVIEDB_BASE_API_PATH + (isPopularList ?
                 AppStaticData.MOVIEDB_POPULAR_PATH : AppStaticData.MOVIEDB_TOP_RATED_PATH);

        Uri builtUri = Uri.parse(movieListTypeBase).buildUpon()
            .appendQueryParameter(AppStaticData.MOVIEDB_API_QUERY_PARAM,
                    AppStaticData.MOVIEDB_API_QUERY_VALUE)
            .appendQueryParameter(AppStaticData.MOVIEDB_LANGUAGE_QUERY_PARAM,
                    AppStaticData.MOVIEDB_LANGUAGE_QUERY_VALUE)
            .appendQueryParameter(AppStaticData.MOVIEDB_PAGE_QUERY_PARAM, Integer.toString(page))
            .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error building Url " + e.getMessage());
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {

            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");


            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                Log.e(TAG, "Error fetching data from urlConnection! Connection response is null.");
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
