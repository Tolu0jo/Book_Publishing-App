package com.example.publisher.dao.impl;

import com.example.publisher.TestDataUtil;
import com.example.publisher.dao.AuthorDao;
import com.example.publisher.domain.Author;
import com.example.publisher.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoIntegrationTests {
    private BookDaoImpl underTest;
    private AuthorDao authorDao;
    @Autowired
    public BookDaoIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao ) {
        this.underTest = underTest;
        this.authorDao= authorDao;
    }
   @Test
    public void testBookCreatedAndRecalled(){
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);
       Book book = TestDataUtil.createBook();
       book.setAuthorId(author.getId());
       underTest.create(book);
       Optional<Book> result = underTest.findOne(book.getIsbn());
//       asserthat(result).isPresent();
//       asserthat(result.get()).isEqualTo(book);


    }

    @Test
    public void testBooksCreatedAndRecalled(){
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.createBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        Book book1 = TestDataUtil.createBook1();
        book1.setAuthorId(author.getId());
        underTest.create(book1);

        Book book2 = TestDataUtil.createBook2();
        book2.setAuthorId(author.getId());
        underTest.create(book2);


        List<Book> result = underTest.find();
        //assertThat(result).hasSize(3).containExactly(book,book1,book2);

    }
    @Test
    public void testUpdateBook(){
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);
        Book book1 = TestDataUtil.createBook1();
        book1.setAuthorId(author.getId());
        underTest.create(book1);
        book1.setTitle("updated title");
        underTest.update(book1.getIsbn(),book1);
        Optional<Book> result = underTest.findOne(book1.getIsbn());
    }

    @Test
    public void testDeleteBook(){
        Author author = TestDataUtil.createAuthor();
        authorDao.create(author);
        Book book1 = TestDataUtil.createBook1();
        book1.setAuthorId(author.getId());
        underTest.create(book1);
        underTest.delete(book1.getIsbn());
        underTest.findOne(book1.getIsbn());
        Optional<Book> result = underTest.findOne(book1.getIsbn());
        // assertThat(result).isEmpty();
    }
}
