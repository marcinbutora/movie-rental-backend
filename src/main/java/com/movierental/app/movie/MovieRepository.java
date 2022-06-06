package com.movierental.app.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByMovieId(Long movieId);
    Optional<Movie> findMovieByTitleAndYearMovie(String title, Long yearMovie);
    @Transactional
    void deleteMovieByTitle(String title);
}
