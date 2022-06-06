package com.movierental.app.movie;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter {
    public MovieDTO entityToDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setYearMovie(movie.getYearMovie());
        movieDTO.setMovieCategory(movie.getMovieCategory());
        movieDTO.setUrlCover(movie.getUrlCover());
        return movieDTO;
    }

    public List<MovieDTO> entityToDtoList(List<Movie> movieList) {
        return movieList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Movie dtoToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setMovieId(movieDTO.getMovieId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setYearMovie(movieDTO.getYearMovie());
        movie.setMovieCategory(movieDTO.getMovieCategory());
        movie.setUrlCover(movieDTO.getUrlCover());
        return movie;
    }

    public List<Movie> dtoToEntityList(List<MovieDTO> movieDTOList) {
        return movieDTOList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
