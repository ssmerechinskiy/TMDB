package com.example.soberman.tmdb.data.repository.datasource.network;

import android.util.Log;

import com.example.soberman.tmdb.data.repository.datasource.exception.ConnectionException;
import com.example.soberman.tmdb.data.repository.datasource.exception.HttpException;

import retrofit.RetrofitError;

/**
 * Created by soberman on 19.06.2016.
 */
public abstract class BaseNetworkDataSource {
    private final static String TAG = BaseNetworkDataSource.class.getSimpleName();

    protected void processException(Throwable e) throws Exception {
        if(e instanceof RetrofitError) {
            RetrofitError ex = (RetrofitError) e;
            Log.d(TAG, "Error kind:" + ex.getKind().name());
            if(ex.getKind() == RetrofitError.Kind.HTTP) {
                Log.d(TAG, "throw http exception");
                throw new HttpException(ex.getMessage());
            }
            if(ex.getKind() == RetrofitError.Kind.NETWORK) {
                Log.d(TAG, "throw connection exception");
                throw new ConnectionException(ex.getMessage());
            }
            throw new Exception(ex.getMessage());
        } else {
            throw new Exception(e.getMessage());
        }
    }
}
