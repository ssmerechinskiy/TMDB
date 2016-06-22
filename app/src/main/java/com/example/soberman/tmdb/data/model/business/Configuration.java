package com.example.soberman.tmdb.data.model.business;

import com.example.soberman.tmdb.data.model.dao.ConfigurationRealm;
import com.example.soberman.tmdb.data.model.dao.RealmUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by soberman on 18.06.2016.
 */
public class Configuration {

    @Expose @SerializedName("images")
    private ConfigurationImages images;

    @Expose @SerializedName("change_keys")
    private List<String> changeKeys;

    private long createdTime;

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    public ConfigurationImages getImages() {
        return images;
    }

    public void setImages(ConfigurationImages images) {
        this.images = images;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public static Configuration createFrom(ConfigurationRealm source) {
        if(source == null) return null;
        Configuration dest = new Configuration();
        if(source.getImages() != null) dest.setImages(ConfigurationImages.createFrom(source.getImages()));
        if(source.getChangeKeys() != null) dest.setChangeKeys(RealmUtil.convert(source.getChangeKeys()));
        dest.setCreatedTime(source.getCreatedTime());
        return dest;
    }
}
