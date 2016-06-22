package com.example.soberman.tmdb.di;

import com.example.soberman.tmdb.TMDBApplication;
import com.example.soberman.tmdb.api.MovieDataBaseApiService;
import com.example.soberman.tmdb.data.repository.MoviesRepository;
import com.example.soberman.tmdb.data.repository.MoviesRepositoryImpl;
import com.example.soberman.tmdb.di.api.NetModule;
import com.example.soberman.tmdb.di.scope.PerApplication;
import com.squareup.otto.Bus;

import dagger.Component;

/**
 * Created by soberman on 19.06.2016.
 */
@PerApplication
@Component(modules = {TMDBAppModule.class, NetModule.class})
public interface TMDBAppComponent {
    void inject(TMDBApplication app);
    void inject(MoviesRepositoryImpl moviesRepository);
    TMDBApplication getApplication();
    MovieDataBaseApiService getMovieApi();
    Bus getBus();
}
