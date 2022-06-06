package com.movierental.app.movie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

@SpringBootTest
@Transactional
class MovieServiceTest {
    @Autowired
    private MovieFacade movieFacade;
    @Autowired
    private MovieConverter movieConverter;

    private MovieDTO createMovie(String title, String description, Long yearMovie, String movieCategory, String urlCover) {
        MovieDTO movie = new MovieDTO();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setYearMovie(yearMovie);
        movie.setMovieCategory(movieCategory);
        movie.setUrlCover(urlCover);
        return movie;
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
        // when
        movieFacade.saveNewMovie(movie1);
        movieFacade.saveNewMovie(movie2);
        int moviesCount = movieFacade.getMoviesCount();
        // then
        assertThat(moviesCount).isEqualTo(2);
    }
    @Test
    void shouldRemoveMovieByID() {
        // given
        MovieDTO movie = createMovie( "Testowy film", "Opis", 2000L, "Horror", "url-to-cover");
        // when
        movieFacade.saveNewMovie(movie);
        movieFacade.deleteMovie(movie.getTitle(), movie.getYearMovie());
        // then
        List<MovieDTO> moviesList = movieFacade.getMoviesList();
        assertThat(moviesList).isEmpty();
    }

    @Test
    void shouldSaveMovie() {
        // given
        MovieDTO movie = createMovie( "Test", "Opis", 1998L, "Horror", "url-to-cover");
        // when
        MovieDTO savedMovie = movieFacade.saveNewMovie(movie);
        // then
        assertThat(movie).isEqualTo(savedMovie);
    }

   @Test
   void shouldUpdateMovie() {
        // given
       MovieDTO movie1 = createMovie("Testowy", "Film", 1998L, "Horror", "url-to-path");
       MovieDTO movie2 = createMovie("Testowy 2", "Film Komedia", 1998L, "Horror", "url-to-path");
        // when
       movieFacade.saveNewMovie(movie1);
       movieFacade.saveNewMovie(movie2);
       MovieDTO updatedMovie = movieFacade.updateMovie(movie1.getTitle(), movie1.getYearMovie(), movie2);
       // then
       assertThat(updatedMovie).isEqualTo(movie2);
   }


}