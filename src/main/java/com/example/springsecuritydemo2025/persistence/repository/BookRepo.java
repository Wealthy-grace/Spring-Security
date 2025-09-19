package com.example.springsecuritydemo2025.persistence.repository;

import com.example.springsecuritydemo2025.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitle(String title);
    Optional<BookEntity>findByAuthor(String author);
    boolean existsByTitle(String title);
    boolean existsByAuthor(String author);

    boolean existsByDescription(String description);


}
