package com.example.android.popularmovies.data;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class AppStaticData {

    // FIXME : never commit
    public static final String MOVIEDB_API_QUERY_VALUE = "PasteYourAPIKey";

    public static final String MOVIEDB_IMAGE_BASE_PATH = "http://image.tmdb.org/t/p/";

    public static final String MOVIEDB_IMAGE_W185 ="w185/";

    public static final String MOVIEDB_IMAGE_W342 ="w342/";

    public static String getW185PathImage(){
        return MOVIEDB_IMAGE_BASE_PATH + MOVIEDB_IMAGE_W185;
    }

    public static String getW342PathImage(){
        return MOVIEDB_IMAGE_BASE_PATH + MOVIEDB_IMAGE_W342;
    }

    public static final String MOVIEDB_BASE_API_PATH = "https://api.themoviedb.org/3/movie/";

    public static final String MOVIEDB_POPULAR_PATH = "popular";

    public static final String MOVIEDB_TOP_RATED_PATH = "top_rated";

    public static final String MOVIEDB_API_QUERY_PARAM = "api_key";

    public static final String MOVIEDB_LANGUAGE_QUERY_PARAM = "language";

    public static final String MOVIEDB_LANGUAGE_QUERY_VALUE = "en_US";

    public static final String MOVIEDB_PAGE_QUERY_PARAM = "page";

    public static final String MOVIEDB_RESPONSE_LIST_NAME = "results";

    public static final String MOVIEDB_RESPONSE_ORIGINAL_TITLE_NAME = "original_title";

    public static final String MOVIEDB_RESPONSE_POSTER_PATH_NAME = "poster_path";

    public static final String MOVIEDB_RESPONSE_OVERVIEW_PATH_NAME = "overview";

    public static final String MOVIEDB_RESPONSE__RELEASE_DATE_PATH_NAME = "release_date";

    public static final String MOVIEDB_RESPONSE_VOTE_AVERAGE_PATH_NAME = "vote_average";

}
