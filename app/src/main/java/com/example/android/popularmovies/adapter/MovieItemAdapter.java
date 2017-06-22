package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.AppStaticData;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvaro.carvalho on 20/06/17.
 */

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.MovieViewHolder>{
    // TODO: REMOVE
    private static final String TAG = MovieItemAdapter.class.getSimpleName();

    private List<MovieItem> mMovieItems = new ArrayList<>();

    private Context mContext;

    final private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Custom constructor for the item adapter.
     * @param context activity context.
     * @param listener interface for the item click listener.
     */
    public MovieItemAdapter(@NonNull Context context, @NonNull List<MovieItem> movieItemList,
                            @NonNull ListItemClickListener listener) {
        this.mContext = context;
        this.mOnClickListener = listener;
        this.mMovieItems = movieItemList;
    }

    /**
     * Inflates the item to the movie view holder.
     * @param parent the parent view holding the context.
     * @param viewType
     * @return the view holder to an movie item.
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.recycler_view_movies, parent, false);

        return new MovieViewHolder(movieView);
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
        Picasso.with(getContext()).load(NetworkUtils.createImageUri(AppStaticData.getW185PathImage(),
                movieItem.jpegImageName)).into(movieImageView);
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

    public void setMovieItems(List<MovieItem> movieItems){
        this.mMovieItems = movieItems;
    }

    /**
     * Class of the holder view
     */
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mMovieTitleView;
        public ImageView mMovieImageView;

        /**
         * Constructor to the item view holder of the recycler view.
         *
         * @param itemView
         */
        public MovieViewHolder(View itemView) {
            super(itemView);
            mMovieTitleView = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.movie_image);
            itemView.setOnClickListener(this);
        }

        /**
         * Called upon a click in one list item.
         *
         * @param v clicked.
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}
