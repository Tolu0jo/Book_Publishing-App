package com.example.publisher.controllers;

import com.example.publisher.domain.dto.BookDto;
import com.example.publisher.entitities.BookEntity;
import com.example.publisher.mappers.Mapper;
import com.example.publisher.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/books")
@RestController
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;
   @Autowired
    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping(path = "{isbn}")
    public ResponseEntity<BookDto>createBook(@PathVariable("isbn")String isbn,@RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity =bookService.createBook(isbn,bookEntity);
        BookDto savedBookDto=bookMapper.mapTo(savedBookEntity);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<BookDto> getBooks(){
      List<BookEntity> results = bookService.findAll();
      return results.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }
}
