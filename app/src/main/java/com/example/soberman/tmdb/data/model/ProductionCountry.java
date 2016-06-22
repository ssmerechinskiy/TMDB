package com.example.soberman.tmdb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class ProductionCountry extends RealmObject{
    @Expose
    @SerializedName("iso_3166_1")
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

    public static ProductionCountry createFrom(ProductionCountry source) {
        if(source == null) return null;
        ProductionCountry dest = new ProductionCountry();
        dest.setISOCode(source.getISOCode());
        dest.setName(source.getName());
        return dest;
    }

    public static RealmList<ProductionCountry> createListFrom(List<ProductionCountry> source) {
        if(source == null) return null;
        RealmList<ProductionCountry> dest = new RealmList<>();
        for (ProductionCountry item : source) {
            dest.add(ProductionCountry.createFrom(item));
        }
        return dest;
    }
}
