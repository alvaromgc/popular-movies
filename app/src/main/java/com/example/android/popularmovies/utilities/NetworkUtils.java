package com.example.android.popularmovies.utilities;

import android.net.Uri;

/**
 * Created by alvaro.carvalho on 21/06/17.
 */

public class NetworkUtils {

    /**
     *
     * @param imageApiBasePath base url path to the movie image API
     * @param jpegImageName string with the jpg movie name
     * @return the URI of an movie image on the <a href="http://themoviedb.org">themoviedb.org</a> API
     */
    public static Uri createImageUri(String imageApiBasePath, String jpegImageName){
        return Uri.parse(imageApiBasePath+jpegImageName);
    }

}
