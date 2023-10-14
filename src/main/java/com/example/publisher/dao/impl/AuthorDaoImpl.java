package com.example.publisher.dao.impl;

import com.example.publisher.dao.AuthorDao;
import com.example.publisher.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl  implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Author author) {
     jdbcTemplate.update("INSERT INTO authors (id,name,age) VALUES(?,?,?)",
        author.getId(),author.getName(),author.getAge()
);
    }
}
