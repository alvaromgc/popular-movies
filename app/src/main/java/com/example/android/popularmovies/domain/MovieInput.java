package com.example.android.popularmovies.domain;

/**
 * Created by c1254654 on 22/06/17.
 */

public class MovieInput {

    public final int page;
    public final boolean isPopularMovies;

    public MovieInput(int page, boolean isPopularMovies){
        this.page = page;
        this.isPopularMovies = isPopularMovies;
    }


}
