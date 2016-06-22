package com.example.soberman.tmdb.data.repository.datasource.network;

import com.example.soberman.tmdb.api.MovieDataBaseApiService;
import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;

/**
 * Created by soberman on 19.06.2016.
 */
public class MovieNetworkDataSourceImpl extends BaseNetworkDataSource implements MovieNetworkDataSource {

    private MovieDataBaseApiService mApi;

    public MovieNetworkDataSourceImpl(MovieDataBaseApiService api) {
        mApi = api;
    }

    @Override
    public TopRatedMoviesResponse obtainTopRatedMovies(int page) throws Exception {
        TopRatedMoviesResponse response = null;
        try {
            response = mApi.getTopRatedMovies(page);
        } catch (Throwable t) {
            processException(t);
        }
        return response;
    }

    @Override
    public Movie obtainMovie(long movieId) throws Exception {
        Movie movie = null;
        try {
            movie = mApi.getMovie(movieId);
        } catch (Throwable t) {
            processException(t);
        }
        return movie;
    }

    @Override
    public Configuration obtainConfiguration() throws Exception {
        Configuration configuration = null;
        try {
            configuration = mApi.getConfiguration();
        } catch (Throwable t) {
            processException(t);
        }
        return configuration;
    }
}
