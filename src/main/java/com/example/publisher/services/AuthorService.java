package com.example.publisher.services;


import com.example.publisher.entitities.AuthorEntity;

import java.util.List;
import java.util.Optional;


public interface AuthorService {

    AuthorEntity save (AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    boolean isExist(Long id);

    AuthorEntity partialUpdate(Long id,AuthorEntity author);

    void delete(Long id);
}
