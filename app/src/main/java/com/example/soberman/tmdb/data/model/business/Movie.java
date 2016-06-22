package com.example.soberman.tmdb.data.model.business;

import com.example.soberman.tmdb.data.model.Genre;
import com.example.soberman.tmdb.data.model.ProductionCompany;
import com.example.soberman.tmdb.data.model.ProductionCountry;
import com.example.soberman.tmdb.data.model.SpokenLanguage;
import com.example.soberman.tmdb.data.model.dao.MovieRealm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by soberman on 18.06.2016.
 */
public class Movie {
    @Expose @SerializedName("adult")
    private boolean adult;

    @Expose @SerializedName("backdrop_path")
    private String backdropPath;

    @Expose @SerializedName("budget")
    private double budget;

    @Expose @SerializedName("genres")
    private List<Genre> genres;

    @Expose @SerializedName("homepage")
    private String homepage;

    @Expose @SerializedName("id")
    private long id;

    @Expose @SerializedName("imdb_id")
    private String imDbId;

    @Expose @SerializedName("original_language")
    private String originalLanguage;

    @Expose @SerializedName("original_title")
    private String originalTitle;

    @Expose @SerializedName("overview")
    private String overview;

    @Expose @SerializedName("popularity")
    private double popularity;

    @Expose @SerializedName("poster_path")
    private String posterPath;

    @Expose @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @Expose @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;

    @Expose @SerializedName("release_date")
    private Date releaseDate;

    @Expose @SerializedName("revenue")
    private double revenue;

    @Expose @SerializedName("runtime")
    private double runtime;

    @Expose @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @Expose @SerializedName("status")
    private String status;

    @Expose @SerializedName("tagline")
    private String tagline;

    @Expose @SerializedName("title")
    private String title;

    @Expose @SerializedName("video")
    private boolean video;

    @Expose @SerializedName("vote_average")
    private double voteAverage;

    @Expose @SerializedName("vote_count")
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

    public void setGenres(List<Genre> genres) {
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

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
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

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
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

    public static Movie createFrom(MovieRealm source) {
        if(source == null) return null;
        Movie dest = new Movie();
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

    public static List<Movie> createListFrom(List<MovieRealm> source) {
        if(source == null) return null;
        List<Movie> dest = new ArrayList<>();
        for (MovieRealm item : source) {
            dest.add(Movie.createFrom(item));
        }
        return dest;
    }

}
