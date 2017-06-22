package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.AppStaticData;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    ImageView mMoviePoster;
    TextView mOriginalTitle;
    TextView mPlotSynopsis;

    // TODO: deal w date
    TextView mReleaseDate;

    // TODO: deal w rating
    TextView mUserRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);
        mOriginalTitle = (TextView) findViewById(R.id.tv_original_title);
        mPlotSynopsis = (TextView) findViewById(R.id.tv_plot_sysnopsis);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mUserRating = (TextView) findViewById(R.id.tv_user_rating);



        Intent intentOrigin = getIntent();

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        if (intentOrigin != null && intentOrigin.hasExtra("jsonMovieItem")) {
            try {
                JSONObject  jsonMovieItem =
                        new JSONObject(intentOrigin.getStringExtra("jsonMovieItem"));
                Picasso.with(this).load(NetworkUtils
                        .createImageUri(AppStaticData.getW342PathImage(),jsonMovieItem
                                .getString(AppStaticData.MOVIEDB_RESPONSE_POSTER_PATH_NAME)))
                        .into(mMoviePoster);
                mOriginalTitle.setText(jsonMovieItem
                        .getString(AppStaticData.MOVIEDB_RESPONSE_ORIGINAL_TITLE_NAME));
                mPlotSynopsis.setText(jsonMovieItem
                        .getString(AppStaticData.MOVIEDB_RESPONSE_OVERVIEW_PATH_NAME));
                mReleaseDate.setText(jsonMovieItem
                        .getString(AppStaticData.MOVIEDB_RESPONSE__RELEASE_DATE_PATH_NAME));
                mUserRating.setText(jsonMovieItem
                        .getString(AppStaticData.MOVIEDB_RESPONSE_VOTE_AVERAGE_PATH_NAME));
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing json result data.");
            }
        }

    }
}
