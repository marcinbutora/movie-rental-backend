package com.movierental.app.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MovieServiceTest {
    @Autowired
    private MovieFacade movieFacade;

    private MovieDTO createMovie(Long movieId, String title, String description, Long yearMovie, String movieCategory, String urlCover) {
        MovieDTO movie = new MovieDTO(movieId, title, description, yearMovie, movieCategory, urlCover);
        movieFacade.saveNewMovie(movie);
        return movie;
    }

    @Test
    void shouldReturnListOfMovies() {
        // given
        MovieDTO movie1 = createMovie(1L,"Film 1", "Opis 1", 2000L,"Komedia", "url to cover 1");
        MovieDTO movie2 = createMovie(2L,"Film 2", "Opis 2", 2005L,"Dramat", "url to cover 2");
        // when
        List<MovieDTO> moviesList = movieFacade.getMoviesList();
        // then
        assertThat(moviesList).isNotEmpty();
        assertThat(moviesList).containsExactlyInAnyOrder(movie1, movie2);
    }

    @Test
    void shouldReturnMoviesCountIdDB() {
        // given
        MovieDTO movie1 = createMovie(3L,"Film 1", "Opis 1", 2004L,"Komedia", "url to cover 3");
        MovieDTO movie2 = createMovie(4L,"Film 2", "Opis 2", 2002L,"Dramat", "url to cover 4");
        // when
        int moviesCount = movieFacade.getMoviesCount();
        // then
        assertThat(moviesCount).isEqualTo(2);
    }
}