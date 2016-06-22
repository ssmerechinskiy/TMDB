package com.example.soberman.tmdb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class SpokenLanguage extends RealmObject {
    @Expose
    @SerializedName("iso_639_1")
    private String ISOCode;

    @Expose
    @SerializedName("name")
    private String name;

    public String getISOCode() {
        return ISOCode;
    }

    public void setISOCode(String ISOCode) {
        this.ISOCode = ISOCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SpokenLanguage createFrom(SpokenLanguage source) {
        if(source == null) return null;
        SpokenLanguage dest = new SpokenLanguage();
        dest.setISOCode(source.getISOCode());
        dest.setName(source.getName());
        return dest;
    }

    public static RealmList<SpokenLanguage> createListFrom(List<SpokenLanguage> source) {
        if(source == null) return null;
        RealmList<SpokenLanguage> dest = new RealmList<>();
        for (SpokenLanguage item : source) {
            dest.add(SpokenLanguage.createFrom(item));
        }
        return dest;
    }
}
