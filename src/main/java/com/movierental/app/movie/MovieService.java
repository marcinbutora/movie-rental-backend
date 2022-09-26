package com.movierental.app.movie;

import com.movierental.app.exception.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class MovieService {
    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    MovieService(MovieRepository movieRepository, MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    List<MovieDTO> getMoviesList() {
        log.info("Getting movies list, count movies in database: " + getMoviesCount());
        return movieConverter.entityToDtoList(movieRepository.findAll());
    }

    MovieDTO saveMovie(MovieDTO movieDTO) {
        log.info("Saving new movie with title " + movieDTO.getTitle() + " [" + movieDTO.getMovieCategory() + "]");
        movieRepository.save(movieConverter.dtoToEntity(movieDTO));
        return movieDTO;
    }

    void deleteMovie(String title, Long yearMovie) {
        getMovieByTitleAndYear(title, yearMovie, "Movie with title: ", "  was not found!", " was not found!");
        log.info("Movie " + title + "  was removed!");
        movieRepository.deleteMovieByTitle(title);
    }


    MovieDTO updateMovie(String title, Long yearMovie, MovieDTO movie) {
        log.info("Searching for movie by title: " + title);
        Optional<Movie> foundedMovie = getMovieByTitleAndYear(title, yearMovie, "Movie with this title: ", " is not exist!", " is not exist!");
        foundedMovie.get().update(movieConverter.dtoToEntity(movie));
        movieRepository.save(foundedMovie.get());
        log.info("Updated movie saved as a: " + movie.getTitle());
        return movieConverter.entityToDto(foundedMovie.get());
    }

    MovieDTO showMovieByTitle(String title) {
        Optional<Movie> movieByTitle = movieRepository.findMovieByTitle(title);
        if (movieByTitle.isEmpty()) {
            throw new MovieNotFoundException("Movie with this title " + title + " not found!");
        }
        return movieConverter.entityToDto(movieByTitle.get());
    }

    int getMoviesCount() {
        return movieRepository.findAll().size();
    }
    private Optional<Movie> getMovieByTitleAndYear(String title, Long yearMovie, String x, String x1, String x2) {
        Optional<Movie> foundedMovie = movieRepository.findMovieByTitleAndYearMovie(title, yearMovie);
        if (foundedMovie.isEmpty()) {
            log.warn(x + title + x1);
            throw new MovieNotFoundException(x + title + x2);
        }
        return foundedMovie;
    }


}
