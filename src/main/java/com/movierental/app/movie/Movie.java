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
    private Long id;
    private String title;
    private String description;
    private MovieCategory movieCategory;
    private String urlCover;

    Movie(Long id, String title, String description, MovieCategory movieCategory, String urlCover) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.movieCategory = movieCategory;
        this.urlCover = urlCover;
    }

    Movie(String title, String description, MovieCategory movieCategory, String urlCover) {
        this.title = title;
        this.description = description;
        this.movieCategory = movieCategory;
        this.urlCover = urlCover;
    }

    Movie(){}

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
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

    MovieCategory getMovieCategory() {
        return movieCategory;
    }

    void setMovieCategory(MovieCategory movieCategory) {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && movieCategory == movie.movieCategory && Objects.equals(urlCover, movie.urlCover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, movieCategory, urlCover);
    }
}
