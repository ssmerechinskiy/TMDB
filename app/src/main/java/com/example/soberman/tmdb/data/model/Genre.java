package com.example.soberman.tmdb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class Genre extends RealmObject {
    @Expose
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Genre createFrom(Genre source) {
        if(source == null) return null;
        Genre dest = new Genre();
        dest.setName(source.getName());
        dest.setId(source.getId());
        return dest;
    }

    public static RealmList<Genre> createListFrom(List<Genre> source) {
        if(source == null) return null;
        RealmList<Genre> dest = new RealmList<>();
        for (Genre item : source) {
            dest.add(Genre.createFrom(item));
        }
        return dest;
    }
}
