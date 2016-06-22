package com.example.soberman.tmdb.data.repository;

import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;

/**
 * Created by soberman on 19.06.2016.
 */
public interface MoviesRepository {
    TopRatedMoviesResponse getTopRatedMoviesResponse(int page, boolean useCache) throws Exception;
    Movie getMovie(long id, boolean useCache) throws Exception;
    Configuration getConfiguration(boolean useCache) throws Exception;
}
