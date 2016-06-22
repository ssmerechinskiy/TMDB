package com.example.soberman.tmdb.api;

import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by soberman on 19.06.2016.
 */
public interface MovieDataBaseApiService {

    @GET("/movie/{id}")
    Movie getMovie(@Path("id") long movieId);

    @GET("/movie/top_rated")
    TopRatedMoviesResponse getTopRatedMovies(@Query("page") int page);

    @GET("/configuration")
    Configuration getConfiguration();

}
