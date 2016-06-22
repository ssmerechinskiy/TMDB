package com.example.soberman.tmdb.presenter.util;

import com.example.soberman.tmdb.TMDBApplication;
import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.data.repository.MoviesRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by soberman on 20.06.2016.
 */
public class ObservableFactory {

    public static Observable<TopRatedMoviesResponse> createMoviesResponseObservable(final int page, final boolean useCache, final MoviesRepository moviesRepository) {
        return Observable.create(new Observable.OnSubscribe<TopRatedMoviesResponse>() {
            @Override
            public void call(Subscriber<? super TopRatedMoviesResponse> subscriber) {
                try {
                    subscriber.onNext(moviesRepository.getTopRatedMoviesResponse(page, useCache));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    public static Observable<Movie> createMovieObservable(final long id, final boolean useCache, final MoviesRepository moviesRepository) {
        return Observable.create(new Observable.OnSubscribe<Movie>() {
            @Override
            public void call(Subscriber<? super Movie> subscriber) {
                try {
                    subscriber.onNext(moviesRepository.getMovie(id, useCache));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static Observable<Configuration> createConfigurationObservable(final boolean useCache, final MoviesRepository moviesRepository) {
        return Observable.create(new Observable.OnSubscribe<Configuration>() {
            @Override
            public void call(Subscriber<? super Configuration> subscriber) {
                try {
                    subscriber.onNext(moviesRepository.getConfiguration(useCache));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static Observable<Configuration> createUpdateConfigurationObservable(final MoviesRepository moviesRepository) {
        return Observable.create(new Observable.OnSubscribe<Configuration>() {
            @Override
            public void call(Subscriber<? super Configuration> subscriber) {
                try {
                    //check existing configuration in DB
                    Configuration configuration = moviesRepository.getConfiguration(true);
                    if(configuration == null) {
                        //if configuration does not exist in DB just get it from API
                        configuration = moviesRepository.getConfiguration(false);
                    } else {
                        long actualLifeTime = System.currentTimeMillis() - configuration.getCreatedTime();
                        //if life last configuration life time more than update period then update configuration with server
                        if(actualLifeTime > TMDBApplication.UPDATE_CONFIGURATION_PERIOD_HOURS * 60 * 60 * 1000) {
                            try {
                                configuration = moviesRepository.getConfiguration(false);
                            } catch (Exception e) {
                            }
                        }
                    }
                    subscriber.onNext(configuration);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
