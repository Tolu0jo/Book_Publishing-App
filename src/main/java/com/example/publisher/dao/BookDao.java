package com.example.publisher.dao;

import com.example.publisher.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void create(Book book);

    Optional<Book> findOne(String bookId);

    List<Book>find();

    void update(String isbn, Book book);

    void delete(String s);
}
