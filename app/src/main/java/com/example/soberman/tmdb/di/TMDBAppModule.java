package com.example.soberman.tmdb.di;

import com.example.soberman.tmdb.TMDBApplication;
import com.example.soberman.tmdb.di.scope.PerApplication;
import com.example.soberman.tmdb.event.MainThreadBus;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by soberman on 19.06.2016.
 */
@Module
public class TMDBAppModule {

    private final TMDBApplication app;

    public TMDBAppModule(TMDBApplication app) {
        this.app = app;
    }

    @Provides @PerApplication
    public TMDBApplication provideApp() {
        return app;
    }

    @Provides @PerApplication
    public Bus provideOttoEventBus() {
        return new MainThreadBus();
    }
}
