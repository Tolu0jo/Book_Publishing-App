package com.example.publisher.controllers;


import com.example.publisher.domain.dto.AuthorDto;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.mappers.Mapper;
import com.example.publisher.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequestMapping(path = "/api/authors")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    private final Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        //converting Dtos to entity
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        List<AuthorEntity> authors = authorService.findAll();
        return authors.stream().map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> author = authorService.findById(id);
        return author.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto) {
    if(!authorService.isExist(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    authorDto.setId(id);
    AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
    AuthorDto savedAuthor = authorMapper.mapTo(authorService.save(authorEntity));
    return new ResponseEntity<>(savedAuthor,HttpStatus.OK);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(
            @PathVariable("id") Long id,
            @RequestBody AuthorDto authorDto) {
        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity savedAuthor = authorService.partialUpdate( id,authorEntity);
        AuthorDto author = authorMapper.mapTo(savedAuthor);
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id){
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}