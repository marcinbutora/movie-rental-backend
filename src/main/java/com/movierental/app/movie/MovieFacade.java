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

    public void deleteMovie(Long movieId) {
        movieService.deleteMovie(movieId);
    }

    public MovieDTO updateMovie(Long movieId, MovieDTO movie) {
        return movieService.updateMovie(movieId, movie);
    }

    public int getMoviesCount() {
        return movieService.getMoviesCount();
    }
}
