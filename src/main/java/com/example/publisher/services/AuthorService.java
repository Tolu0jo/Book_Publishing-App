package com.example.publisher.services;


import com.example.publisher.entitities.AuthorEntity;
import org.springframework.stereotype.Component;


public interface AuthorService {

    AuthorEntity createAuthor (AuthorEntity author);
}
