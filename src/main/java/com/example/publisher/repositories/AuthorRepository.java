package com.example.publisher.repositories;


import com.example.publisher.entitities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    Iterable<AuthorEntity> ageLessThan(int age);
    @Query("SELECT a from AuthorEntity a WHERE a.age > ?1") //this is not SQL but HQL
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
