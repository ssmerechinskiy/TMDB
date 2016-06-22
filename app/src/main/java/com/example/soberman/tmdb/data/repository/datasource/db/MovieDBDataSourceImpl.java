package com.example.soberman.tmdb.data.repository.datasource.db;

import android.util.Log;

import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.data.model.dao.ConfigurationRealm;
import com.example.soberman.tmdb.data.model.dao.MovieRealm;
import com.example.soberman.tmdb.data.repository.datasource.exception.DBException;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by soberman on 19.06.2016.
 */
public class MovieDBDataSourceImpl extends BaseDBDataSource implements MovieDBDataSource {
    private final static String TAG = MovieDBDataSourceImpl.class.getSimpleName();

    @Override
    public List<Movie> obtainMovies() throws DBException {
        List<Movie> movies = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            RealmQuery<MovieRealm> query = realm.where(MovieRealm.class);
            RealmResults<MovieRealm> moviesRealm = query.findAll();
            if(moviesRealm != null) {
                movies = Movie.createListFrom(moviesRealm.subList(0, moviesRealm.size() - 1));
            }
        } catch (Exception e) {
            throw new DBException("cant get object:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
        return movies;
    }

    @Override
    public Movie obtainMovie(long id) throws DBException {
        Movie movie = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            RealmQuery<MovieRealm> query = realm.where(MovieRealm.class);
            query.equalTo("id", id);
            MovieRealm movieRealm = query.findFirst();
            if(movieRealm != null) {
                movie = Movie.createFrom(movieRealm);
            }
        } catch (Exception e) {
            throw new DBException("cant get object:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
        return movie;
    }

    @Override
    public void persistMovie(Movie movie) throws DBException {
        if(movie == null) return;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            realm.beginTransaction();
            MovieRealm movieRealm = MovieRealm.createFrom(movie);
            realm.copyToRealmOrUpdate(movieRealm);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
            throw new DBException("cant persist or update movie:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
    }

    @Override
    public void persistMovies(List<Movie> movies) throws DBException {
        if(movies == null || movies.size() == 0) return;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            for (Movie m : movies) {
                try {
                    realm.beginTransaction();
                    MovieRealm movieRealm = MovieRealm.createFrom(m);
                    realm.copyToRealmOrUpdate(movieRealm);
                    realm.commitTransaction();
                } catch (Exception e) {
                    realm.cancelTransaction();
                    Log.d(TAG, "persist movie transaction error:" + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new DBException("persistMovies error:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
    }

    @Override
    public void deleteMovies() throws DBException {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            RealmQuery<MovieRealm> query = realm.where(MovieRealm.class);
            RealmResults<MovieRealm> realms = query.findAll();
            if(realms.size() > 0) {
                realm.beginTransaction();
                realms.clear();
                realm.commitTransaction();
            }
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
            throw new DBException("cant delete movies:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
    }

    @Override
    public Configuration obtainConfiguration() throws DBException {
        Configuration configuration = null;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            RealmQuery<ConfigurationRealm> query = realm.where(ConfigurationRealm.class);
            ConfigurationRealm confRealm = query.findFirst();
            if(confRealm != null) {
                configuration = Configuration.createFrom(confRealm);
            }
        } catch (Exception e) {
            throw new DBException("cant get object:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
        return configuration;
    }

    @Override
    public void persistConfiguration(Configuration configuration) throws DBException {
        if(configuration == null) return;
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            realm.beginTransaction();
            ConfigurationRealm configurationRealm = ConfigurationRealm.createFrom(configuration);
            realm.copyToRealm(configurationRealm);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
            throw new DBException("cant persist configuration:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
    }

    @Override
    public void deleteConfiguration() throws DBException {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.refresh();
            RealmQuery<ConfigurationRealm> query = realm.where(ConfigurationRealm.class);
            RealmResults<ConfigurationRealm> realms = query.findAll();
            if(realms.size() > 0) {
                realm.beginTransaction();
                realms.clear();
                realm.commitTransaction();
            }
        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
            throw new DBException("cant delete configurations:" + e.getMessage());
        } finally {
            closeInstance(realm);
        }
    }
}
