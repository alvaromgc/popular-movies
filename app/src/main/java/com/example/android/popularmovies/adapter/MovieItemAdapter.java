package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.ViewHolder.MovieViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class MovieItemAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private List<MovieItem> mMovieItems;

    private Context mContext;

    /**
     * Custom constructor for the movie adapter.
     * @param context
     * @param movieItemList
     */
    public MovieItemAdapter(@NonNull Context context, @NonNull List<MovieItem> movieItemList) {
        this.mMovieItems = movieItemList;
        this.mContext = context;
    }

    /**
     * Inflates the item to the movie view holder.
     * @param parent
     * @param viewType
     * @return the view holder to an movie item
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.recycler_view_movies, parent, false);

        // Return a new holder instance
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;
    }


    /**
     * Sets the values to the items in the view holder.
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int position) {
        // Get the data model based on position
        MovieItem movieItem = mMovieItems.get(position);

        // Set item views based on your views and data model
        TextView movieTitleView = viewHolder.mMovieTitleView;
        movieTitleView.setText(movieItem.originalName);
        ImageView movieImageView = viewHolder.mMovieImageView;
        Picasso.with(getContext()).load(createImageUri(movieItem)).into(movieImageView);
    }

    /**
     * calculate the size of the current array of items.
     * @return the size of the movie items.
     */
    @Override
    public int getItemCount() {
        int itemCount = 0;
        if(mMovieItems != null && !mMovieItems.isEmpty()){
            itemCount = mMovieItems.size();
        }
        return itemCount;
    }

    private Context getContext() {
        return mContext;
    }

    /**
     *
     * @param movieItem
     * @return the URI of an movie image on the <a href="http://themoviedb.org">themoviedb.org</a> API
     */
    private Uri createImageUri(MovieItem movieItem){
        return Uri.parse(movieItem.imageApiBasePath+movieItem.jpegImageName);
    }
}
