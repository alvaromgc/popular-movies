package com.example.android.popularmovies.viewholder;

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

    /**
     * Constructor to the item view holder of the recycler view.
     * @param itemView
     */
    public MovieViewHolder(View itemView){
        super(itemView);
        mMovieTitleView = (TextView) itemView.findViewById(R.id.movie_title);
        mMovieImageView = (ImageView) itemView.findViewById(R.id.movie_image);
    }
}
