package com.example.soberman.tmdb.data.repository.datasource.db;

import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.data.repository.datasource.exception.DBException;

import java.util.List;

/**
 * Created by soberman on 19.06.2016.
 */
public interface MovieDBDataSource {
    List<Movie> obtainMovies() throws DBException;
    Movie obtainMovie(long id) throws DBException;
    void persistMovie(Movie movie) throws DBException;
    void persistMovies(List<Movie> movies) throws DBException;
    void deleteMovies() throws DBException;
    Configuration obtainConfiguration() throws DBException;
    void persistConfiguration(Configuration configuration) throws DBException;
    void deleteConfiguration() throws DBException;
}
