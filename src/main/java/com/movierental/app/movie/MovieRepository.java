package com.movierental.app.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitleAndYearMovie(String title, int yearMovie);
    Optional<Movie> findByTitle(String title);
    @Transactional
    void deleteByTitleAndYearMovie(String title, int yearMovie);
}
