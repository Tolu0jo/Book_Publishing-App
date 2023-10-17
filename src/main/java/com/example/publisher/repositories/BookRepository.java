package com.example.publisher.repositories;


import com.example.publisher.entitities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,String> {
}
