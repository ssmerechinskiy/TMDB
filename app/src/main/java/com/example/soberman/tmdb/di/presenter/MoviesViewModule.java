package com.example.soberman.tmdb.di.presenter;

import com.example.soberman.tmdb.presenter.MoviesPresenter;
import com.example.soberman.tmdb.presenter.MoviesView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by soberman on 20.04.2016.
 */
@Module
public class MoviesViewModule {

    private MoviesView view;

    public MoviesViewModule(MoviesView v) {
        view = v;
    }

    @Provides
    public MoviesView provideView() {
        return view;
    }

    @Provides
    MoviesPresenter provideCompletePresenter(MoviesView v) {
        return new MoviesPresenter(v);
    }
}
