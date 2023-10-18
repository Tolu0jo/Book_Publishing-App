package com.example.publisher.repositories;


import com.example.publisher.entitities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends CrudRepository<BookEntity,String>,
        PagingAndSortingRepository<BookEntity,String> {
}
