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

    public void deleteMovie(String title) {
        movieService.deleteMovie(title);
    }

    public MovieDTO updateMovie(String title, Long year, MovieDTO movie) {
        return movieService.updateMovie(title, year, movie);
    }

    public int getMoviesCount() {
        return movieService.getMoviesCount();
    }
}
