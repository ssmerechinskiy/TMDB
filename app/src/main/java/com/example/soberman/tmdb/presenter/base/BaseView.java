package com.example.soberman.tmdb.presenter.base;

/**
 * Created by soberman on 21.04.2016.
 */
public interface BaseView {
    void showMessage(String message);
    void showProgress();
    void hideProgress();
}
