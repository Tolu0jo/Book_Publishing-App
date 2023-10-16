package com.example.publisher.dao.impl;

import com.example.publisher.TestDataUtil;
import com.example.publisher.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testCreateAuthor(){
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES(?,?,?)"),
                eq(1L),eq("Jones stones"),eq(29));
    }

    @Test
    public void testFindOneAuthor(){
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors WHERE id = ? Limit 1"),
                  ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                 eq(1L)

        );
    }

    @Test

    public void testFindManyAuthors (){
        underTest.find();
        verify(jdbcTemplate).query(
               eq( "SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }
    @Test
    public void testUpdateAuthor(){
        Author author = TestDataUtil.createAuthor();
      underTest.update(3L,author);
      verify(jdbcTemplate).update(
              "UPDATE authors SET id=?,name=?,age=? WHERE id=?",
              1L, "Jones stones", 29,3L
      );
    }
    @Test
    public void testDeleteAuthor(){
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors where id =?",
                1L
        );
    }
}
