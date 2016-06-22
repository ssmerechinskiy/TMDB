package com.example.soberman.tmdb.data.model.dao;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by soberman on 30.12.2015.
 */
public class RealmUtil {
    public static RealmList<RealmString> convert(List<String> list) {
        RealmList<RealmString> res = new RealmList<>();
        if(list == null) return res;
        for(String s : list) {
            RealmString realmString = new RealmString();
            realmString.setValue(s);
            res.add(realmString);
        }
        return res;
    }

    public static List<String> convert(RealmList<RealmString> list) {
        List<String> res = new ArrayList<>();
        if(list == null) return res;
        for(RealmString s : list) {
            String string = new String(s.getValue());
            res.add(string);
        }
        return res;
    }
}
