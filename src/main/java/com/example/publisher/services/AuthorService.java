package com.example.publisher.services;


import com.example.publisher.entitities.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface AuthorService {

    AuthorEntity createAuthor (AuthorEntity author);

    List<AuthorEntity> findAll();
}
