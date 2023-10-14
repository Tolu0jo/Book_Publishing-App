package com.example.publisher.dao.impl;

import com.example.publisher.dao.BookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao{
    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl( final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
