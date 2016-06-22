package com.example.soberman.tmdb.data.model.dao;

import com.example.soberman.tmdb.data.model.business.Configuration;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class ConfigurationRealm extends RealmObject{
    private ConfigurationImagesRealm images;
    private RealmList<RealmString> changeKeys;
    private long createdTime;

    public RealmList<RealmString> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(RealmList<RealmString> changeKeys) {
        this.changeKeys = changeKeys;
    }

    public ConfigurationImagesRealm getImages() {
        return images;
    }

    public void setImages(ConfigurationImagesRealm images) {
        this.images = images;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public static ConfigurationRealm createFrom(Configuration source) {
        if(source == null) return null;
        ConfigurationRealm dest = new ConfigurationRealm();
        if(source.getImages() != null) dest.setImages(ConfigurationImagesRealm.createFrom(source.getImages()));
        if(source.getChangeKeys() != null) dest.setChangeKeys(RealmUtil.convert(source.getChangeKeys()));
        dest.setCreatedTime(source.getCreatedTime());
        return dest;
    }



}
