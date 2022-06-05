package com.movierental.app.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitleAndYear(String title, int year);
    Optional<Movie> findByTitle(String title);
    void deleteByTitleAndYear(String title, int year);
}
