package com.example.android.popularmovies.adapter;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class MovieItem {

    public String originalName;
    public String jpegImageName;
    public String imageApiBasePath;

    public MovieItem(String originalName, String jpegImageName, String imageApiBasePath){
        this.originalName = originalName;
        this.jpegImageName = jpegImageName;
        this.imageApiBasePath = imageApiBasePath;
    }


}
