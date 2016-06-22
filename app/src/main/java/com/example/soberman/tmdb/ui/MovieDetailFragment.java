package com.example.soberman.tmdb.ui;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soberman.tmdb.R;
import com.example.soberman.tmdb.data.model.Genre;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.event.MovieLoadedEvent;
import com.example.soberman.tmdb.presenter.MoviesPresenter;
import com.example.soberman.tmdb.presenter.base.BasePresenter;
import com.example.soberman.tmdb.presenter.base.Presentable;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    public static final String MOVIE_ID = "movie_id";

    CollapsingToolbarLayout appBarLayout;
    TextView title;
    TextView year;
    TextView genres;
    TextView overview;
    MoviesPresenter mPresenter;
    long mMovieId;

    private ImageView posterImageView;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");


    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        posterImageView = (ImageView) rootView.findViewById(R.id.poster_image);
        title = (TextView) rootView.findViewById(R.id.title);
        year = (TextView) rootView.findViewById(R.id.year);
        genres = (TextView) rootView.findViewById(R.id.genres);
        overview = (TextView) rootView.findViewById(R.id.overview);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getActivity() instanceof Presentable) {
            mPresenter = (MoviesPresenter) ((Presentable) getActivity()).getPresenter();
            if(mPresenter != null) {
                mPresenter.getEventBus().register(this);
            }
            if(getArguments() != null && getArguments().containsKey(MOVIE_ID)) {
                mMovieId = getArguments().getLong(MOVIE_ID);
                if(mMovieId != 0) mPresenter.requestMovie(mMovieId);
            }

        }
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            mPresenter.getEventBus().unregister(this);
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Subscribe
    public void onMovieLoaded(MovieLoadedEvent event) {
        setMovie(event.movie);
    }

    public void setMovie(Movie movie) {
        if (appBarLayout != null) {
            posterImageView.setVisibility(View.GONE);
            appBarLayout.setExpandedTitleColor(Color.TRANSPARENT);
            appBarLayout.setTitle(movie.getOriginalTitle());
        } else {
            posterImageView.setVisibility(View.VISIBLE);
            Picasso.with(getActivity())
                    .load(mPresenter.getBaseImageUrl() + mPresenter.getPosterImageSize() + movie.getPosterPath())
                    .into(posterImageView);
        }
        title.setText(movie.getTitle());
        year.setText(dateFormat.format(movie.getReleaseDate()));
        overview.setText(movie.getOverview());
        String gs = getGenresString(movie.getGenres());
        genres.setText(gs);
    }

    private String getGenresString(List<Genre> genres) {
        if(genres == null || genres.size() == 0) return "";
        String s = "";
        for(int i = 0; i < genres.size(); i++) {
            if(i == genres.size() - 1) {
                s += genres.get(i).getName();
            } else {
                s += genres.get(i).getName() + ", ";
            }
        }
        return s;
    }
}
