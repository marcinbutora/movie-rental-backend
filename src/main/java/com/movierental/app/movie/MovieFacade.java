package com.movierental.app.movie;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieFacade {
    private final MovieService movieService;

    public MovieFacade(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieDTO saveNewMovie(MovieDTO movie) {
        return movieService.saveMovie(movie);
    }

    public List<MovieDTO> getMoviesList() {
        return movieService.getMoviesList();
    }

    public void deleteMovie(String title, int year) {
        movieService.deleteMovie(title, year);
    }

    public MovieDTO updateMovie(String title, MovieDTO movie) {
        return movieService.updateMovie(title, movie);
    }

    public int getMoviesCount() {
        return movieService.getMoviesCount();
    }
}
