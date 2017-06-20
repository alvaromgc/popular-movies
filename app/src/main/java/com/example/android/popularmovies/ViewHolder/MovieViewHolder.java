package com.example.android.popularmovies.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public TextView mMovieTitleView;
    public ImageView mMovieImageView;

    public MovieViewHolder(View itemView){
        super(itemView);
        mMovieTitleView = (TextView) itemView.findViewById(R.id.movie_title);
        //movieTitleView.setText(movieItem.originalName);
        mMovieImageView = (ImageView) itemView.findViewById(R.id.movie_image);
        //Picasso.with(getContext()).load(createImageUri(movieItem)).into(movieImageView);
    }
}
