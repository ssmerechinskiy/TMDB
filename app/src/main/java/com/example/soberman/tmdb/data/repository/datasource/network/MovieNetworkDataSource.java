package com.example.soberman.tmdb.data.repository.datasource.network;

import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;

/**
 * Created by soberman on 19.06.2016.
 */
public interface MovieNetworkDataSource {
    TopRatedMoviesResponse obtainTopRatedMovies(int page) throws Exception;
    Movie obtainMovie(long movieId) throws Exception;
    Configuration obtainConfiguration() throws Exception;
}
