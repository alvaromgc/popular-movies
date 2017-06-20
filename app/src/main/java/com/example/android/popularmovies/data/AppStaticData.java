package com.example.android.popularmovies.data;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class AppStaticData {

    public static final String MOVIEDB_IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/";

    public static final String MOVIEDB_IMAGE_W185 ="w185/";

    public static String getW185Image(){
        return MOVIEDB_IMAGE_BASE_PATH + MOVIEDB_IMAGE_W185;
    }

}