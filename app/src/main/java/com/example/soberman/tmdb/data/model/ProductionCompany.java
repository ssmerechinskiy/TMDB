package com.example.soberman.tmdb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by soberman on 18.06.2016.
 */
public class ProductionCompany extends RealmObject {
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

    public static ProductionCompany createFrom(ProductionCompany source) {
        if(source == null) return null;
        ProductionCompany dest = new ProductionCompany();
        dest.setName(source.getName());
        dest.setId(source.getId());
        return dest;
    }

    public static RealmList<ProductionCompany> createListFrom(List<ProductionCompany> source) {
        if(source == null) return null;
        RealmList<ProductionCompany> dest = new RealmList<>();
        for (ProductionCompany item : source) {
            dest.add(ProductionCompany.createFrom(item));
        }
        return dest;
    }
}
