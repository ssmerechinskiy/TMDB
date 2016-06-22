package com.example.soberman.tmdb.di.presenter;

import com.example.soberman.tmdb.di.scope.PerActivity;
import com.example.soberman.tmdb.ui.MovieDetailActivity;
import com.example.soberman.tmdb.ui.MovieListActivity;

import dagger.Component;

/**
 * Created by soberman on 21.06.2016.
 */
@PerActivity
@Component(modules = {MoviesViewModule.class})
public interface MoviesViewComponent {
    void inject(MovieListActivity activity);
    void inject(MovieDetailActivity activity);
}
