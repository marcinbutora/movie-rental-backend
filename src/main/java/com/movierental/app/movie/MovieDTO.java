package com.movierental.app.movie;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class MovieDTO {
    private String title;
    private String description;
    private Long yearMovie;
    private String movieCategory;
    private String urlCover;

    public MovieDTO(String title, String description, Long yearMovie, String movieCategory, String urlCover) {
        this.title = title;
        this.description = description;
        this.yearMovie = yearMovie;
        this.movieCategory = movieCategory;
        this.urlCover = urlCover;
    }

    public MovieDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getYearMovie() {
        return yearMovie;
    }

    public void setYearMovie(Long yearMovie) {
        this.yearMovie = yearMovie;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }
}
