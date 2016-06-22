package com.example.soberman.tmdb.presenter;

import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.presenter.base.BaseView;

import java.util.List;

/**
 * Created by soberman on 20.06.2016.
 */
public interface MoviesView extends BaseView {
    void refreshMovies(List<Movie> movies);
    void addMoviesPage(List<Movie> movies, int page, int pageCount);
    void updateMovie(Movie movie);
    void showPageLoadingProgress();
    void hidePageLoadingProgress();
    void hideSwipeRefreshMoviesProgress();
    void onAPIConfigurationSuccess();
    void onAPIConfigurationError();
}
