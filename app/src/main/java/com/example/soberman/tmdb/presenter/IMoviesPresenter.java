package com.example.soberman.tmdb.presenter;

import android.content.Context;

import com.example.soberman.tmdb.data.model.business.Movie;

/**
 * Created by soberman on 20.06.2016.
 */
public interface IMoviesPresenter {
    void requestMovie(long id);
    void requestRefreshMovies();
    void requestMoviesPage(boolean refresh);
    void onItemClicked(Context context, Movie movie);
}
