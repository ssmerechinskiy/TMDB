package com.example.soberman.tmdb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.soberman.tmdb.R;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.di.presenter.DaggerMoviesViewComponent;
import com.example.soberman.tmdb.di.presenter.MoviesViewComponent;
import com.example.soberman.tmdb.di.presenter.MoviesViewModule;
import com.example.soberman.tmdb.presenter.MoviesPresenter;
import com.example.soberman.tmdb.presenter.MoviesView;
import com.example.soberman.tmdb.presenter.base.BasePresenter;
import com.example.soberman.tmdb.presenter.base.Presentable;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 */
public class MovieDetailActivity extends AppCompatActivity implements Presentable, MoviesView{
    private MoviesViewComponent moviesViewComponent;
    private ImageView topImageView;
    @Inject
    protected MoviesPresenter mPresenter;
    private long mMovieID;
    private MovieDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        topImageView = (ImageView) findViewById(R.id.toolbar_image);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mMovieID = getIntent().getLongExtra(MovieDetailFragment.MOVIE_ID, 0);

        if (savedInstanceState == null) {
            /*
            Bundle arguments = new Bundle();
            arguments.putLong(MovieDetailFragment.MOVIE_ID,
                    getIntent().getLongExtra(MovieDetailFragment.MOVIE_ID, 0));*/
            fragment = new MovieDetailFragment();
            //fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
        moviesViewComponent = DaggerMoviesViewComponent.builder()
                .moviesViewModule(new MoviesViewModule(this))
                .build();
        moviesViewComponent.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //navigateUpTo(new Intent(this, MovieListActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void refreshMovies(List<Movie> movies) {

    }

    @Override
    public void addMoviesPage(List<Movie> movies, int page, int pageCount) {

    }

    @Override
    public void updateMovie(Movie movie) {
        Picasso.with(this)
                .load(mPresenter.getBaseImageUrl() + mPresenter.getPosterImageSize() + movie.getPosterPath())
                .into(topImageView);
    }

    @Override
    public void showPageLoadingProgress() {

    }

    @Override
    public void hidePageLoadingProgress() {

    }

    @Override
    public void hideSwipeRefreshMoviesProgress() {

    }

    @Override
    public void onAPIConfigurationSuccess() {
        if(mMovieID != 0) mPresenter.requestMovie(mMovieID);
    }

    @Override
    public void onAPIConfigurationError() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
