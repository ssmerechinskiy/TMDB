package com.example.soberman.tmdb.data.repository;

import android.util.Log;

import com.example.soberman.tmdb.TMDBApplication;
import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.data.repository.datasource.db.MovieDBDataSource;
import com.example.soberman.tmdb.data.repository.datasource.exception.ConnectionException;
import com.example.soberman.tmdb.data.repository.datasource.network.MovieNetworkDataSource;
import com.example.soberman.tmdb.di.DaggerTMDBAppComponent;
import com.example.soberman.tmdb.di.TMDBAppComponent;
import com.example.soberman.tmdb.event.NetworkConnectionFailedEvent;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by soberman on 19.06.2016.
 */
public class MoviesRepositoryImpl implements MoviesRepository{
    private final static String TAG = MoviesRepositoryImpl.class.getSimpleName();

    private MovieNetworkDataSource networkDataSource;
    private MovieDBDataSource dbDataSource;

    private TMDBAppComponent appComponent;

    @Inject
    protected Bus eventBus;

    public MoviesRepositoryImpl(MovieNetworkDataSource networkDataSource, MovieDBDataSource dbDataSource) {
        this.networkDataSource = networkDataSource;
        this.dbDataSource = dbDataSource;
        appComponent = TMDBApplication.getComponent();
        appComponent.inject(this);
    }

    @Override
    public synchronized TopRatedMoviesResponse getTopRatedMoviesResponse(int page, boolean useCache) throws Exception {
        TopRatedMoviesResponse response = null;
        try {
            response = networkDataSource.obtainTopRatedMovies(page);
            dbDataSource.persistMovies(response.getMovies());
        } catch (Exception e) {
            Log.d(TAG, "getTopRatedMoviesResponse Exception:" + e.getMessage());
            response = new TopRatedMoviesResponse();
            List<Movie> movies = dbDataSource.obtainMovies();
            response.setPage(1);
            response.setTotalPages(1);
            response.setMovies(movies);
            if(movies != null && movies.size() > 0) {
                checkConnectionFailedException(e, "Connection problems. Loaded from DB");
            } else {
                checkConnectionFailedException(e, "Connection problems. Retry later");
            }
        }
        return response;
    }

    @Override
    public synchronized Movie getMovie(long id, boolean useCache) throws Exception {
        Movie response = null;
        try {
            response = networkDataSource.obtainMovie(id);
            dbDataSource.persistMovie(response);
        } catch (Exception e) {
            checkConnectionFailedException(e, "Connection problems. Retry later");
            Log.d(TAG, "getMovies Exception:" + e.getMessage());
            response = dbDataSource.obtainMovie(id);
        }
        return response;
    }

    @Override
    public synchronized Configuration getConfiguration(boolean useCache) throws Exception {
        Configuration config = null;
        if (!useCache) {
            try {
                config = networkDataSource.obtainConfiguration();
                config.setCreatedTime(System.currentTimeMillis());
                dbDataSource.deleteConfiguration();
                dbDataSource.persistConfiguration(config);
            } catch (Exception e) {
                checkConnectionFailedException(e, "Connection problems. Retry later");
                Log.d(TAG, "getConfiguration Exception:" + e.getMessage());
                config = dbDataSource.obtainConfiguration();
            }
        } else {
            config = dbDataSource.obtainConfiguration();
        }
        return config;
    }

    private void checkConnectionFailedException(Exception e, String failedMessage) {
        if(e instanceof ConnectionException) {
            eventBus.post(new NetworkConnectionFailedEvent(failedMessage));
        }
    }
}
