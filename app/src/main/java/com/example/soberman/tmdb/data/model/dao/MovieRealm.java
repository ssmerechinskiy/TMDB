package com.example.soberman.tmdb.data.model.dao;

import com.example.soberman.tmdb.data.model.Genre;
import com.example.soberman.tmdb.data.model.business.Movie;
import com.example.soberman.tmdb.data.model.ProductionCompany;
import com.example.soberman.tmdb.data.model.ProductionCountry;
import com.example.soberman.tmdb.data.model.SpokenLanguage;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by soberman on 18.06.2016.
 */
public class MovieRealm extends RealmObject {
    private boolean adult;
    private String backdropPath;
    private double budget;
    private RealmList<Genre> genres;
    private String homepage;
    @PrimaryKey
    private long id;
    private String imDbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private RealmList<ProductionCompany> productionCompanies;
    private RealmList<ProductionCountry> productionCountries;
    private Date releaseDate;
    private double revenue;
    private double runtime;
    private RealmList<SpokenLanguage> spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double voteAverage;
    private long voteCount;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImDbId() {
        return imDbId;
    }

    public void setImDbId(String imDbId) {
        this.imDbId = imDbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(RealmList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(RealmList<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(RealmList<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public static MovieRealm createFrom(Movie source) {
        if(source == null) return null;
        MovieRealm dest = new MovieRealm();
        dest.setAdult(source.isAdult());
        dest.setBackdropPath(source.getBackdropPath());
        dest.setBudget(source.getBudget());
        if(source.getGenres() != null) dest.setGenres(Genre.createListFrom(source.getGenres()));
        dest.setHomepage(source.getHomepage());
        dest.setId(source.getId());
        dest.setImDbId(source.getImDbId());
        dest.setOriginalLanguage(source.getOriginalLanguage());
        dest.setOriginalTitle(source.getOriginalTitle());
        dest.setOverview(source.getOverview());
        dest.setPopularity(source.getPopularity());
        dest.setPosterPath(source.getPosterPath());
        if(source.getProductionCompanies() != null) dest.setProductionCompanies(ProductionCompany.createListFrom(source.getProductionCompanies()));
        if(source.getProductionCountries() != null) dest.setProductionCountries(ProductionCountry.createListFrom(source.getProductionCountries()));
        dest.setReleaseDate(source.getReleaseDate());
        dest.setRevenue(source.getRevenue());
        dest.setRuntime(source.getRuntime());
        if(source.getSpokenLanguages() != null) dest.setSpokenLanguages(SpokenLanguage.createListFrom(source.getSpokenLanguages()));
        dest.setStatus(source.getStatus());
        dest.setTagline(source.getTagline());
        dest.setTitle(source.getTitle());
        dest.setVideo(source.isVideo());
        dest.setVoteAverage(source.getVoteAverage());
        dest.setVoteCount(source.getVoteCount());
        return dest;
    }

    public static RealmList<MovieRealm> createListFrom(List<Movie> source) {
        if(source == null) return null;
        RealmList<MovieRealm> dest = new RealmList<>();
        for (Movie item : source) {
            dest.add(MovieRealm.createFrom(item));
        }
        return dest;
    }

}
