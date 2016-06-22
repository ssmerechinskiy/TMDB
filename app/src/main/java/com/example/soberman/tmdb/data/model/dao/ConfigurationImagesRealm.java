package com.example.soberman.tmdb.data.model.dao;

import com.example.soberman.tmdb.data.model.business.ConfigurationImages;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class ConfigurationImagesRealm extends RealmObject {
    private String baseUrl;
    private String secureBaseUrl;
    private RealmList<RealmString> backdropSizes;
    private RealmList<RealmString> logoSizes;
    private RealmList<RealmString> posterSizes;
    private RealmList<RealmString> profileSizes;
    private RealmList<RealmString> stillSizes;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public RealmList<RealmString> getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(RealmList<RealmString> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public RealmList<RealmString> getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(RealmList<RealmString> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public RealmList<RealmString> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(RealmList<RealmString> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public RealmList<RealmString> getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(RealmList<RealmString> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public RealmList<RealmString> getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(RealmList<RealmString> stillSizes) {
        this.stillSizes = stillSizes;
    }

    public static ConfigurationImagesRealm createFrom(ConfigurationImages source) {
        if(source == null) return  null;
        ConfigurationImagesRealm dest = new ConfigurationImagesRealm();
        dest.setBaseUrl(source.getBaseUrl());
        dest.setSecureBaseUrl(source.getSecureBaseUrl());
        if(source.getBackdropSizes() != null) dest.setBackdropSizes(RealmUtil.convert(source.getBackdropSizes()));
        if(source.getLogoSizes() != null) dest.setLogoSizes(RealmUtil.convert(source.getLogoSizes()));
        if(source.getPosterSizes() != null) dest.setPosterSizes(RealmUtil.convert(source.getPosterSizes()));
        if(source.getProfileSizes() != null) dest.setProfileSizes(RealmUtil.convert(source.getProfileSizes()));
        if(source.getStillSizes() != null) dest.setStillSizes(RealmUtil.convert(source.getStillSizes()));
        return dest;
    }
}
