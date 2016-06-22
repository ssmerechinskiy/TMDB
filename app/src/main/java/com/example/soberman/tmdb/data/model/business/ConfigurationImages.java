package com.example.soberman.tmdb.data.model.business;

import com.example.soberman.tmdb.data.model.dao.ConfigurationImagesRealm;
import com.example.soberman.tmdb.data.model.dao.RealmUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by soberman on 18.06.2016.
 */
public class ConfigurationImages {
    @Expose @SerializedName("base_url")
    private String baseUrl;

    @Expose @SerializedName("secure_base_url")
    private String secureBaseUrl;

    @Expose @SerializedName("backdrop_sizes")
    private List<String> backdropSizes;

    @Expose @SerializedName("logo_sizes")
    private List<String> logoSizes;

    @Expose @SerializedName("poster_sizes")
    private List<String> posterSizes;

    @Expose @SerializedName("profile_sizes")
    private List<String> profileSizes;

    @Expose @SerializedName("still_sizes")
    private List<String> stillSizes;

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

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public List<String> getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }

    public static ConfigurationImages createFrom(ConfigurationImagesRealm source) {
        if(source == null) return  null;
        ConfigurationImages dest = new ConfigurationImages();
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
