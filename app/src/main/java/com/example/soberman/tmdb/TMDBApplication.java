package com.example.soberman.tmdb;

import android.app.Application;

import com.example.soberman.tmdb.di.DaggerTMDBAppComponent;
import com.example.soberman.tmdb.di.TMDBAppComponent;
import com.example.soberman.tmdb.di.TMDBAppModule;
import com.example.soberman.tmdb.di.api.NetModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by soberman on 19.06.2016.
 */
public class TMDBApplication extends Application {

    public final static int UPDATE_CONFIGURATION_PERIOD_HOURS = 24;

    private static TMDBApplication sInstance;

    private TMDBAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initRealmDB();
        buildGraphAndInject();
    }

    public static TMDBAppComponent getComponent() {
        return sInstance.appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerTMDBAppComponent.builder()
                .tMDBAppModule(new TMDBAppModule(this))
                .netModule(new NetModule())
                .build();
        appComponent.inject(this);
    }

    private void initRealmDB() {
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

}
