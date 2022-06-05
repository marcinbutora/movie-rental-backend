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
        log.info("Saving new movie");
        movieRepository.save(movieConverter.dtoToEntity(movieDTO));
        return movieDTO;
    }

    void deleteMovie(String title, int year) {
        Optional<Movie> foundedMovie = movieRepository.findByTitleAndYear(title, year);
        if (foundedMovie.isEmpty()) {
            log.warn("Movie with title: " + title + " and year: " + year + " was not found!");
            throw new MovieNotFoundException("Movie with title: " + title + " and year: " + year + " was not found!");
        }
        log.info("Movie " + title + " [" + year + "] was removed!");
        movieRepository.deleteByTitleAndYear(title, year);
    }

    MovieDTO updateMovie(String title, MovieDTO movie) {
        log.info("Searching for movie by title: " + title);
        Optional<Movie> foundedMovie = movieRepository.findByTitle(title);
        if (foundedMovie.isEmpty()) {
            log.warn("Movie with this title: " + title + " is not exist!");
            throw new MovieNotFoundException("Movie with this title: " + title + " is not exist!");
        }
        foundedMovie.get().update(movieConverter.dtoToEntity(movie));
        movieRepository.save(foundedMovie.get());
        log.info("Updated movie saved as a: " + movie.getTitle());
        return movieConverter.entityToDto(foundedMovie.get());
    }

    private int getMoviesCount() {
        return movieRepository.findAll().size();
    }


}
