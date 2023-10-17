//package com.example.publisher.repository;
//
//import com.example.publisher.TestDataUtil;
//import com.example.publisher.domain.Author;
//import com.example.publisher.domain.Book;
//import com.example.publisher.repositories.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Optional;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class BookRepositoryIntegrationTests {
//    private BookRepository underTest;
//
//    @Autowired
//    public void BookDaoIntegrationTests(BookRepository underTest) {
//        this.underTest = underTest;
//
//    }
//   @Test
//    public void testBookCreatedAndRecalled(){
//        Author author = TestDataUtil.createAuthor();
//       Book book = TestDataUtil.createBook(author);
//       underTest.save(book);
//       Optional<Book> result = underTest.findById(book.getIsbn());
////       asserthat(result).isPresent();
////       asserthat(result.get()).isEqualTo(book);
//
//    }
//
//    @Test
//    public void testBooksCreatedAndRecalled(){
//        Author author = TestDataUtil.createAuthor();
//
//        Book book = TestDataUtil.createBook(author);
//
//        underTest.save(book);
//
//        Book book1 = TestDataUtil.createBook1(author);
//
//        underTest.save(book1);
//
//        Book book2 = TestDataUtil.createBook2(author);
//
//        underTest.save(book2);
//
//
//        Iterable<Book> result = underTest.findAll();
//        assertThat(result).hasSize(3).containsExactly(book,book1,book2);
//
//    }
//    @Test
//    public void testUpdateBook(){
//        Author author = TestDataUtil.createAuthor();
//
//        Book book1 = TestDataUtil.createBook1(author);
//        underTest.save(book1);
//        book1.setTitle("updated title");
//        underTest.save(book1);
//        Optional<Book> result = underTest.findById(book1.getIsbn());
//    }
//
//    @Test
//    public void testDeleteBook(){
//        Author author = TestDataUtil.createAuthor();
//
//        Book book1 = TestDataUtil.createBook1(author);
//        underTest.save(book1);
//        underTest.deleteById(book1.getIsbn());
//        Optional<Book> result = underTest.findById(book1.getIsbn());
//        assertThat(result).isEmpty();
//    }
//}
