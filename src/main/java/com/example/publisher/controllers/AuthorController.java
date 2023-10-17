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
import java.util.stream.Collectors;


@RequestMapping(path = "/api/authors")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    private final Mapper<AuthorEntity,AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
       this.authorMapper = authorMapper;
   }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
       //converting Dtos to entity
       AuthorEntity authorEntity = authorMapper.mapFrom(author);
       AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AuthorDto> getAuthors(){
        List<AuthorEntity> authors = authorService.findAll();
       return authors.stream().map(authorMapper::mapTo)
               .collect(Collectors.toList());
    }

}
