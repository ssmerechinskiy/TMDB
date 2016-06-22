package com.example.soberman.tmdb.presenter.base;

import android.util.Log;

import com.example.soberman.tmdb.TMDBApplication;
import com.example.soberman.tmdb.data.model.business.Configuration;
import com.example.soberman.tmdb.data.repository.MoviesRepository;

//import com.example.soberman.tmdb.di.repository.TMDBRepositoryComponent;
//import com.example.soberman.tmdb.di.repository.TMDBRepositoryModule;
import com.example.soberman.tmdb.di.repository.DaggerTMDBRepositoryComponent;
import com.example.soberman.tmdb.di.repository.TMDBRepositoryComponent;
import com.example.soberman.tmdb.di.repository.TMDBRepositoryModule;
import com.example.soberman.tmdb.presenter.util.ObservableFactory;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by soberman on 20.06.2016.
 */
public abstract class BasePresenter {
    private final static String TAG = BasePresenter.class.getSimpleName();

    @Inject
    protected MoviesRepository mMoviesRepository;

    protected static Configuration sConfiguration;

    private TMDBRepositoryComponent repositoryComponent;

    private Subscription updateConfigSubscription;

    @Inject
    protected Bus eventBus;

    public BasePresenter() {
        repositoryComponent = DaggerTMDBRepositoryComponent.builder()
                .tMDBRepositoryModule(new TMDBRepositoryModule())
                .tMDBAppComponent(TMDBApplication.getComponent())
                .build();
        repositoryComponent.inject(this);
    }

    protected void updateConfiguration() {
        Log.d(TAG, "updateConfiguration start");
        Observable<Configuration> configurationObservable
                = ObservableFactory.createUpdateConfigurationObservable(mMoviesRepository).subscribeOn(Schedulers.newThread());
        updateConfigSubscription = configurationObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Configuration>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "updateConfigError");
                onUpdateAPIConfigurationError(new Exception(e));
            }

            @Override
            public void onNext(Configuration configuration) {
                if(configuration == null) {
                    Log.d(TAG, "updateConfigError");
                    onUpdateAPIConfigurationError(new Exception("Unknown error"));
                }
                else {
                    Log.d(TAG, "updateConfigSuccess");
                    sConfiguration = configuration;
                    onUpdateAPIConfigurationSuccess();
                }

            }
        });
    }

    protected abstract void onUpdateAPIConfigurationSuccess();

    protected abstract void onUpdateAPIConfigurationError(Exception e);

    protected void releaseResources() {
        if(updateConfigSubscription != null && !updateConfigSubscription.isUnsubscribed()) updateConfigSubscription.unsubscribe();
    }
}
