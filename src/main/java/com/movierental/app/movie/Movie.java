package com.movierental.app.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private Long yearMovie;
    private String description;
    private String movieCategory;
    private String urlCover;

    Movie(Long id, String title, Long year, String description, String movieCategory, String urlCover) {
        this.movieId = id;
        this.title = title;
        this.yearMovie = year;
        this.description = description;
        this.movieCategory = movieCategory;
        this.urlCover = urlCover;
    }

    Movie(){}

    Long getYearMovie() {
        return yearMovie;
    }

    void setYearMovie(Long yearMovie) {
        this.yearMovie = yearMovie;
    }

    Long getMovieId() {
        return movieId;
    }

    void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getMovieCategory() {
        return movieCategory;
    }

    void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    String getUrlCover() {
        return urlCover;
    }

    void setUrlCover(String urlCover) {
        this.urlCover = urlCover;
    }

    void update(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.movieCategory = movie.getMovieCategory();
        this.urlCover = movie.getUrlCover();
        this.yearMovie = movie.getYearMovie();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieId, movie.movieId) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && movieCategory.equals(movie.movieCategory) && Objects.equals(urlCover, movie.urlCover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, title, description, movieCategory, urlCover);
    }
}
