package com.example.soberman.tmdb.di.repository;

import com.example.soberman.tmdb.di.TMDBAppComponent;
import com.example.soberman.tmdb.di.scope.PerPresenter;
import com.example.soberman.tmdb.presenter.base.BasePresenter;

import dagger.Component;

/**
 * Created by soberman on 19.06.2016.
 */
@PerPresenter
@Component(modules = {TMDBRepositoryModule.class}, dependencies = {TMDBAppComponent.class})
public interface TMDBRepositoryComponent {
    void inject(BasePresenter presenter);
}
