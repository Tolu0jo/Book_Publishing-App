package com.example.publisher.dao.impl;
import com.example.publisher.TestDataUtil;
import com.example.publisher.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Book book = TestDataUtil.createBook();
        underTest.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES(?,?,?)"),
                eq("1982-1-234-567"),
                eq("Jones is a stone"),
                eq(1L));
    }

    @Test
    public void testFindOneBook(){
        underTest.findOne("1982-1-234-567");
        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1"),
               ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                 eq("1982-1-234-567")

        );
    }

    @Test
    public void testThatFindBooks(){
        underTest.find();
        verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
                );
    }
    @Test
    public void testThatUpdateBook(){
        Book book1= TestDataUtil.createBook1();
        underTest.update("1982-1-234-5670",book1);
        verify(jdbcTemplate).update(
                "UPDATE books SET isbn=?,title=?,author_id=? WHERE isbn=?",
                "1982-1-234-5670", "Jones stone",1L,"1982-1-234-5670"
        );
    }
    @Test
    public void testThatDeleteBook(){
        underTest.delete("1982-1-234-5670");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn=?",
                "1982-1-234-5670"
        );
    }

}
