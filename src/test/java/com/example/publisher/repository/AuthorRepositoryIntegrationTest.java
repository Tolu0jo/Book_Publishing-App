//package com.example.publisher.repository;
//
//import com.example.publisher.TestDataUtil;
//import com.example.publisher.domain.Author;
//import com.example.publisher.repositories.AuthorRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//import java.util.Optional;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorRepositoryIntegrationTest {
//
//    private AuthorRepository underTest;
//
//
//@Autowired
//    public void AuthorDaoImplIntegrationTest(AuthorRepository underTest) {
//        this.underTest = underTest;
//    }
//
//    @Test
//    public void testAuthorCreatedAndRecalled(){
//        Author author = TestDataUtil.createAuthor();
//        underTest.save(author);
//        Optional<Author> result = underTest.findById(author.getId());
//         assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//    }
//
//    @Test
//    public void testMultipleAuthorsRecalled(){
//        Author author = TestDataUtil.createAuthor();
//        Author author1 = TestDataUtil.createAuthor1();
//        Author author2 = TestDataUtil.createAuthor2();
//        underTest.save(author);
//        underTest.save(author1);
//        underTest.save(author2);
//        Iterable<Author> result = underTest.findAll();
//        //asserThat(result).hasSize(3).containsExactly(author,author1,author2);
//
//    }
////
//    @Test
//    public void testAuthorUpdate(){
//    Author author1 = TestDataUtil.createAuthor1();
//    underTest.save(author1);
//    author1.setName("updated name");
//    underTest.save(author1);
//    Optional<Author> result = underTest.findById(author1.getId());
//     assertThat(result).isPresent();
//     assertThat(result.get()).isEqualTo(author1);
//    }
//
//    @Test
//    public void testAuthorDelete(){
//        Author author1 = TestDataUtil.createAuthor1();
//        underTest.save(author1);
//        underTest.deleteById(author1.getId());
//       Optional<Author> result = underTest.findById(author1.getId());
//       assertThat(result).isEmpty();
//}
//
//@Test
//    public void testAuthorsWithAgeLessThan(){
//    Author author = TestDataUtil.createAuthor();
//    underTest.save(author);
//    Author author1 = TestDataUtil.createAuthor1();
//    underTest.save(author1);
//    Author author2 = TestDataUtil.createAuthor2();
//    underTest.save(author2);
//
//    Iterable<Author>result =underTest.ageLessThan(50);
//    assertThat(result).containsExactly(author);
//
//}
//
//@Test
//    public void testAuthorsWithAgeGreaterThan(){
//    Author author = TestDataUtil.createAuthor();
//    underTest.save(author);
//    Author author1 = TestDataUtil.createAuthor1();
//    underTest.save(author1);
//    Author author2 = TestDataUtil.createAuthor2();
//    underTest.save(author2);
//
//   Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(50);
//   assertThat(result).containsExactly(author2);
//
//}
//
//}
