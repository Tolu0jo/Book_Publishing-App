package com.example.publisher.dao.impl;

import com.example.publisher.TestDataUtil;
import com.example.publisher.domain.Author;
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
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;


@Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testAuthorCreatedAndRecalled(){
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testMultipleAuthorsRecalled(){
        Author author = TestDataUtil.createAuthor();
        Author author1 = TestDataUtil.createAuthor1();
        Author author2 = TestDataUtil.createAuthor2();
        underTest.create(author);
        underTest.create(author1);
        underTest.create(author2);
        List<Author> result = underTest.find();
    //   asserThat(result).hasSize(3).containExactly(author,author1,author2);

    }

    @Test
    public void testAuthorUpdate(){
    Author author1 = TestDataUtil.createAuthor1();
    underTest.create(author1);
    author1.setName("updated name");
    underTest.update(author1.getId(), author1);
    Optional<Author> result = underTest.findOne(author1.getId());
    //  assertThat(result).isPresent();
//      assertThat(result.get()).isEqualTo(author1);
    }

    @Test
    public void testAuthorDelete(){
        Author author1 = TestDataUtil.createAuthor1();
        underTest.create(author1);
        underTest.delete(author1.getId());
       Optional<Author> result = underTest.findOne(author1.getId());
      // assertThat(result).isEmpty();
}


}
