package com.example.soberman.tmdb.di.api;

import com.example.soberman.tmdb.api.MovieDataBaseApiService;
import com.example.soberman.tmdb.di.scope.PerApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by soberman on 19.06.2016.
 */
@Module
public class NetModule {
    private static final String TMDB_API_URL = "http://api.themoviedb.org/3";
    private static final String TMDB_API_KEY = "fe32da4ee80983d13fefeaa15039614f";

    @Provides @PerApplication
    //@Named("TMDBApiGson")
    Gson provideTMDBApiGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();

    }

    @Provides @PerApplication
    //@Named("TMDBApiOkHttpClient")
    OkHttpClient provideOkkHttpClient() {
        return new OkHttpClient();
    }

    @Provides @PerApplication
    //@Named("TMDBApiRequestInterceptor")
    RequestInterceptor provideRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addHeader("Host", getHost());
                //request.addHeader("Host", TMDB_API_URL.replace("https://", "").replace("/3", ""));
                request.addQueryParam("api_key", TMDB_API_KEY);
            }
        };
    }

    @Provides @PerApplication
    //@Named("TMDBApiRestAdapter")
    RestAdapter provideTMDBApiRestAdapter(/*@Named("TMDBApiGson")*/ Gson gson, /*@Named("TMDBApiOkHttpClient")*/ OkHttpClient okHttpClient, /*@Named("TMDBApiRequestInterceptor")*/ RequestInterceptor requestInterceptor) {
        return new RestAdapter.Builder()
                .setEndpoint(TMDB_API_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

    @Provides @PerApplication
    MovieDataBaseApiService provideMovieDataBaseApi(/*@Named("TMDBApiRestAdapter")*/ RestAdapter restAdapter) {
        return restAdapter.create(MovieDataBaseApiService.class);
    }

    private String getHost() {
        String s = TMDB_API_URL.replace("https://", "");
        return s.replace("/3", "");
    }
}
