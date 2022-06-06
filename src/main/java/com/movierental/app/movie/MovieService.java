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

    void deleteMovie(Long movieId) {
        Optional<Movie> foundedMovie = movieRepository.findMovieByMovieId(movieId);
        if (foundedMovie.isEmpty()) {
            log.warn("Movie with ID: " + movieId + "  was not found!");
            throw new MovieNotFoundException("Movie with ID: " + movieId + " was not found!");
        }
        log.info("Movie " + movieId + "  was removed!");
        movieRepository.deleteMovieByMovieId(movieId);
    }

    MovieDTO updateMovie(Long movieId, MovieDTO movie) {
        log.info("Searching for movie by ID: " + movieId);
        Optional<Movie> foundedMovie = movieRepository.findMovieByMovieId(movieId);
        if (foundedMovie.isEmpty()) {
            log.warn("Movie with this id: " + movieId + " is not exist!");
            throw new MovieNotFoundException("Movie with this id: " + movieId + " is not exist!");
        }
        foundedMovie.get().update(movieConverter.dtoToEntity(movie));
        movieRepository.save(foundedMovie.get());
        log.info("Updated movie saved as a: " + movie.getTitle());
        return movieConverter.entityToDto(foundedMovie.get());
    }

    int getMoviesCount() {
        return movieRepository.findAll().size();
    }


}
