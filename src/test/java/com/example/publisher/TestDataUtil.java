package com.example.publisher;
//
//import com.example.publisher.domain.Author;
//import com.example.publisher.domain.Book;
import com.example.publisher.domain.dto.AuthorDto;
import com.example.publisher.domain.dto.BookDto;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.entitities.BookEntity;

public final class TestDataUtil {

    public static AuthorEntity createAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Jones stones")
                .age(29)
                .build();
    }

    public static AuthorEntity createAuthor1() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Jones stones")
                .age(50)
                .build();
    }

    public static AuthorEntity createAuthor2() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Jones stones")
                .age(80)
                .build();
    }

    public static BookEntity createBook(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("1982-1-234-567")
                .title("Jones is a stone")
                .author(author)
                .build();
    }

    public static BookDto createBook1(final AuthorDto author) {
        return BookDto.builder()
                .isbn("1982-1-234-5670")
                .title("Jones stone")
                .author(author)
                .build();
    }
    public static BookEntity createBook2(final AuthorEntity author) {
        return BookEntity.builder()
                .isbn("1982-1-234-5671")
                .title("Jones a stone")
                .author(author)
                .build();
    }
}
