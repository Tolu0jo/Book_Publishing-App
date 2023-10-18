package com.example.publisher.controllers;

import com.example.publisher.domain.dto.AuthorDto;
import com.example.publisher.domain.dto.BookDto;
import com.example.publisher.entitities.BookEntity;
import com.example.publisher.mappers.Mapper;
import com.example.publisher.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<BookDto>fullUpdateBook(@PathVariable("isbn")String isbn,@RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        BookEntity savedBookEntity =bookService.createBook(isbn,bookEntity);
        BookDto savedBookDto=bookMapper.mapTo(savedBookEntity);
        if(bookExists){
            return new ResponseEntity<>(savedBookDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public Page<BookDto> getBooks(Pageable pageable){
      Page<BookEntity> results = bookService.findAll(pageable);
      return results.map(bookMapper::mapTo)   ;
    }

    @GetMapping(path = "{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn")String isbn){
       Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        return bookEntity.map(book -> {
            BookDto bookDto = bookMapper.mapTo(book);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>( HttpStatus.NOT_FOUND));
    }
    @PatchMapping(path = "{isbn}")
    public ResponseEntity<BookDto>partialUpdateBook(@PathVariable("isbn")String isbn,@RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);

        if(!bookExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            BookEntity savedBookEntity =bookService.partialUpdate(isbn,bookEntity);
            BookDto savedBookDto=bookMapper.mapTo(savedBookEntity);
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }
    }
    @DeleteMapping(path = "{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
