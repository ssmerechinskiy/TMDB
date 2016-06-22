package com.example.soberman.tmdb.di.repository;

import com.example.soberman.tmdb.api.MovieDataBaseApiService;
import com.example.soberman.tmdb.data.repository.MoviesRepository;
import com.example.soberman.tmdb.data.repository.MoviesRepositoryImpl;
import com.example.soberman.tmdb.data.repository.datasource.db.MovieDBDataSource;
import com.example.soberman.tmdb.data.repository.datasource.db.MovieDBDataSourceImpl;
import com.example.soberman.tmdb.data.repository.datasource.network.MovieNetworkDataSource;
import com.example.soberman.tmdb.data.repository.datasource.network.MovieNetworkDataSourceImpl;
import com.example.soberman.tmdb.di.api.NetModule;
import com.example.soberman.tmdb.di.scope.PerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by soberman on 19.06.2016.
 */
@PerPresenter
@Module
public class TMDBRepositoryModule {

    @Provides
    MovieNetworkDataSource provideNetworkDataSource(MovieDataBaseApiService api) {
        return new MovieNetworkDataSourceImpl(api);
    }

    @Provides MovieDBDataSource provideDBDataSource() {
        return new MovieDBDataSourceImpl();
    }

    @Provides
    MoviesRepository provideMoviesRepository(MovieNetworkDataSource networkDataSource, MovieDBDataSource dbDataSource) {
        return new MoviesRepositoryImpl(networkDataSource, dbDataSource);
    }
}
