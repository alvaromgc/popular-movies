package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.AppStaticData;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

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
        if (intentOrigin != null) {
            if (intentOrigin.hasExtra("jsonMovieItem")) {
                try {
                    JSONObject  jsonMovieItem = new JSONObject(intentOrigin.getStringExtra("jsonMovieItem"));
                    Picasso.with(this).load(NetworkUtils.createImageUri(AppStaticData.getW342PathImage(),
                            jsonMovieItem.getString("poster_path"))).into(mMoviePoster);
                    mOriginalTitle.setText(jsonMovieItem.getString("original_title"));
                    mPlotSynopsis.setText(jsonMovieItem.getString("overview"));
                    mReleaseDate.setText(jsonMovieItem.getString("release_date"));
                    mUserRating.setText(jsonMovieItem.getString("vote_average"));
                } catch (JSONException e) {
                    // FIXME: code an error message to deal w error
                }
            }
        }

    }
}
