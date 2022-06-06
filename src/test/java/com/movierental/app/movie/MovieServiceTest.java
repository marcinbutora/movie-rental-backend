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

    private MovieDTO createMovie(String title, String description, Long yearMovie, String movieCategory, String urlCover) {
        return new MovieDTO(title, description, yearMovie, movieCategory, urlCover);
    }

    @Test
    void shouldReturnListOfMovies() {
        // given
        MovieDTO movie1 = createMovie("Film 1", "Opis 1", 2000L,"Komedia", "url to cover 1");
        MovieDTO movie2 = createMovie("Film 2", "Opis 2", 2005L,"Dramat", "url to cover 2");
        // when
        movieFacade.saveNewMovie(movie1);
        movieFacade.saveNewMovie(movie2);
        List<MovieDTO> moviesList = movieFacade.getMoviesList();
        // then
        assertThat(moviesList).isNotEmpty();
        assertThat(moviesList).containsExactlyInAnyOrder(movie1, movie2);
    }

    @Test
    void shouldReturnMoviesCountIdDB() {
        // given
        MovieDTO movie1 = createMovie("Film 1", "Opis 1", 2004L,"Komedia", "url to cover 3");
        MovieDTO movie2 = createMovie("Film 2", "Opis 2", 2002L,"Dramat", "url to cover 4");
        movieFacade.saveNewMovie(movie1);
        movieFacade.saveNewMovie(movie2);
        // when
        int moviesCount = movieFacade.getMoviesCount();
        // then
        assertThat(moviesCount).isEqualTo(2);
    }

    @Test
    void shouldUpdateMovieInfo() {
        // given
        MovieDTO movie1 = createMovie("Film 1", "Opis 1", 2002L,"Komedia", "url to cover 5");
        MovieDTO movie2 = createMovie("Film 2", "Opis 2", 2004L, "Dramat", "url to cover 6");
        movieFacade.saveNewMovie(movie1);
        movieFacade.saveNewMovie(movie2);
        // when
        MovieDTO updatedMovie = movieFacade.updateMovie(movie1.getTitle(), movie1.getYearMovie(), movie2);
        // then
        assertThat(updatedMovie).isEqualTo(movie2);
    }
    @Test
    void shouldRemoveMovie() {
        // given
        MovieDTO movie1 = createMovie("Film 1", "Opis 1", 2000L,"Komedia", "url to cover 5");
        // when
        movieFacade.saveNewMovie(movie1);
        movieFacade.deleteMovie("Film 1");
        //then
        final List<MovieDTO> moviesList = movieFacade.getMoviesList();
        assertThat(moviesList).doesNotContain(movie1);
    }

}