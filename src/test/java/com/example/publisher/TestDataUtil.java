package com.example.publisher;

import com.example.publisher.domain.Author;
import com.example.publisher.domain.Book;

public final class TestDataUtil {

    public static Author createAuthor() {
        return Author.builder()
                .id(1L)
                .name("Jones stones")
                .age(29)
                .build();
    }

    public static Author createAuthor1() {
        return Author.builder()
                .id(2L)
                .name("Jones stones")
                .age(50)
                .build();
    }

    public static Author createAuthor2() {
        return Author.builder()
                .id(3L)
                .name("Jones stones")
                .age(80)
                .build();
    }
    public static Book createBook(final Author author) {
        return Book.builder()
                .isbn("1982-1-234-567")
                .title("Jones is a stone")
                .author(author)
                .build();
    }

    public static Book createBook1(final Author author) {
        return Book.builder()
                .isbn("1982-1-234-5670")
                .title("Jones stone")
                .author(author)
                .build();
    }
    public static Book createBook2(final Author author) {
        return Book.builder()
                .isbn("1982-1-234-5671")
                .title("Jones a stone")
                .author(author)
                .build();
    }
}
