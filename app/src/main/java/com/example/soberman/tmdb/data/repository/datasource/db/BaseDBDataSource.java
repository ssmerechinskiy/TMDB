package com.example.soberman.tmdb.data.repository.datasource.db;

import io.realm.Realm;

/**
 * Created by soberman on 19.06.2016.
 */
public abstract class BaseDBDataSource {
    protected void closeInstance(Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }
}
