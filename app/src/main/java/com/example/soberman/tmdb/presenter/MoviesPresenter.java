package com.example.soberman.tmdb.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.soberman.tmdb.R;
import com.example.soberman.tmdb.api.response.TopRatedMoviesResponse;
import com.example.soberman.tmdb.data.model.business.Movie;
//import com.example.soberman.tmdb.di.api.DaggerNetComponent;
//import com.example.soberman.tmdb.di.api.NetComponent;
//import com.example.soberman.tmdb.di.api.NetModule;
import com.example.soberman.tmdb.event.MovieLoadedEvent;
import com.example.soberman.tmdb.event.NetworkConnectionFailedEvent;
import com.example.soberman.tmdb.presenter.base.ActivityPresenter;
import com.example.soberman.tmdb.presenter.base.BasePresenter;
import com.example.soberman.tmdb.presenter.util.ObservableFactory;
import com.example.soberman.tmdb.ui.MovieDetailActivity;
import com.example.soberman.tmdb.ui.MovieDetailFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by soberman on 20.06.2016.
 */
public class MoviesPresenter extends BasePresenter implements ActivityPresenter, IMoviesPresenter {
    private final static String TAG = BasePresenter.class.getSimpleName();

    private MoviesView view;
    private int currentPage = 0;
    private final int pageSize = 20;
    private int totalPages = 0;
    private boolean isPagenationAllowed = true;

    private Subscription mMoviesPageSubscription;
    private Subscription mMovieSubscription;

    private List<Movie> movieList;
    private Movie currentMovie;

    private boolean isTwoPane = false;

    public MoviesPresenter(MoviesView v) {
        super();
        eventBus.register(this);
        view = v;
        movieList = new ArrayList<>();
        view.showProgress();
        updateConfiguration();
    }

    /** --------------------------super class methods implementations-----------------------------*/
    @Override
    protected void onUpdateAPIConfigurationSuccess() {
        Log.d(TAG, "onUpdateAPIConfigurationSuccess");
        view.hideProgress();
        view.onAPIConfigurationSuccess();
    }

    @Override
    protected void onUpdateAPIConfigurationError(Exception e) {
        Log.d(TAG, "onUpdateAPIConfigurationSuccess");
        view.hideProgress();
        view.onAPIConfigurationError();
    }

    @Override
    protected void releaseResources() {
        super.releaseResources();
    }

    /** ------------------------------IMoviesPresenter interface----------------------------------*/
    @Override
    public void requestMovie(long id) {
        //view.showProgress();
        Observable<Movie> movieObservable = ObservableFactory.createMovieObservable(id, false, mMoviesRepository).subscribeOn(Schedulers.newThread());
        mMovieSubscription = movieObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "requestMovieCompleted");
                //view.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "requestMovie Error:"+ e.getMessage());
                //view.hideProgress();
            }

            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "requestMovie OnNext");
                currentMovie = movie;
                view.updateMovie(movie);
                MovieLoadedEvent movieLoadedEvent = new MovieLoadedEvent();
                movieLoadedEvent.movie = movie;
                eventBus.post(movieLoadedEvent);
            }
        });
    }

    @Override
    public void requestRefreshMovies() {
        currentPage = 0;
        totalPages = 0;
        isPagenationAllowed = true;
        requestMoviesPage(true);
    }

    @Override
    public void requestMoviesPage(final boolean refresh) {
        if(!isPagenationAllowed) return;
        view.showPageLoadingProgress();
        currentPage ++;
        Observable<TopRatedMoviesResponse> moviesObservable =
                ObservableFactory.createMoviesResponseObservable(currentPage, false, mMoviesRepository).subscribeOn(Schedulers.newThread());
        mMoviesPageSubscription = moviesObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<TopRatedMoviesResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "requestPageCompleted");
                view.hidePageLoadingProgress();
                view.hideSwipeRefreshMoviesProgress();
            }

            @Override
            public void onError(Throwable e) {
                view.hidePageLoadingProgress();
                view.showMessage("requestMovies Error:" + e.getMessage());
                view.hideSwipeRefreshMoviesProgress();
            }

            @Override
            public void onNext(TopRatedMoviesResponse topRatedMoviesResponse) {
                Log.d(TAG, "requestPageOnNext");
                currentPage = topRatedMoviesResponse.getPage();
                totalPages = topRatedMoviesResponse.getTotalPages();
                if(currentPage == totalPages) isPagenationAllowed = false;
                List<Movie> list = topRatedMoviesResponse.getMovies();
                if(!refresh) {
                    movieList.addAll(list);
                    view.addMoviesPage(list, currentPage, totalPages);
                } else {
                    movieList.clear();
                    movieList.addAll(list);
                    view.refreshMovies(list);
                }

            }
        });
    }

    @Override
    public void onItemClicked(Context context, Movie movie) {
        if (isTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putLong(MovieDetailFragment.MOVIE_ID, movie.getId());
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.MOVIE_ID, movie.getId());
            context.startActivity(intent);
        }
    }

    /** ------------------------------------------------------------------------------------------*/

    /** ActivityPresenter interface implementing*/
    @Override
    public void onConfigurationChanged() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        releaseResources();
        if(mMoviesPageSubscription != null && !mMoviesPageSubscription.isUnsubscribed()) mMoviesPageSubscription.unsubscribe();
        if(mMovieSubscription != null && !mMovieSubscription.isUnsubscribed()) mMovieSubscription.unsubscribe();
    }

    @Subscribe
    public void onNetworkConnectionFailed(NetworkConnectionFailedEvent e) {
        Log.d(TAG, "onNetworkConnectionFailed");
        view.showMessage(e.message);

    }


    public boolean isTwoPane() {
        return isTwoPane;
    }

    public void setTwoPane(boolean twoPane) {
        isTwoPane = twoPane;
    }

    public String getBaseImageUrl() {
        return sConfiguration.getImages().getBaseUrl();
    }

    public String getLogoImageSize() {
        return sConfiguration.getImages().getLogoSizes().get(4);
    }

    public String getPosterImageSize() {
        return sConfiguration.getImages().getPosterSizes().get(5);
    }

    public Bus getEventBus() {
        return eventBus;
    }
}
