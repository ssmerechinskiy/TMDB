package com.example.soberman.tmdb.data.model.dao;

import io.realm.RealmObject;

/**
 * Created by soberman on 19.06.2016.
 */
public class RealmString extends RealmObject {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
