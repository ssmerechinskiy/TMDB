package com.example.soberman.tmdb.presenter.base;

/**
 * Created by soberman on 31.03.2016.
 */
public interface ActivityPresenter {
    //activity lifecycle callbacks
    void onStart();
    void onResume();
    void onStop();
    void onDestroy();
    void onConfigurationChanged();

}
