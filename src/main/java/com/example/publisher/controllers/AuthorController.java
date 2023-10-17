package com.example.publisher.controllers;


import com.example.publisher.domain.dto.AuthorDto;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.mappers.Mapper;
import com.example.publisher.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public AuthorDto createAuthor(@RequestBody AuthorDto author) {
       //converting Dtos to entity
       AuthorEntity authorEntity = authorMapper.mapFrom(author);
       AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(savedAuthorEntity);
    }

}
