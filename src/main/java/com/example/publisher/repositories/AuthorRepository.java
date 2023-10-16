package com.example.publisher.repositories;

import com.example.publisher.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    Iterable<Author> ageLessThan(int age);
    @Query("SELECT a from Author a WHERE a.age > ?1") //this is not SQL but HQL
    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);
}
