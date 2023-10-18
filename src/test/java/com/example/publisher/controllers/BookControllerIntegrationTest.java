package com.example.publisher.controllers;

import com.example.publisher.TestDataUtil;
import com.example.publisher.domain.dto.BookDto;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.entitities.BookEntity;
import com.example.publisher.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
private final MockMvc mockMvc;
private BookService bookService;
private final ObjectMapper objectMapper;
@Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, BookService bookService) {
        this.mockMvc = mockMvc;
    this.bookService = bookService;

    this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnHttp201() throws Exception {
        BookDto bookDto = TestDataUtil.createBook1(null);
       String createBookJson= objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/"+ bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
};

    @Test
    public void testThatUpdateBookReturnHttp200() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        BookEntity savedBook = bookService.createBook(testBookA.getIsbn(),testBookA);

        BookDto testBook = TestDataUtil.createBook1(null);
         testBook.setIsbn(savedBook.getIsbn());
        String createBookJson= objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/"+ savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    };

    @Test
    public void testThatUpdateBookReturnUpdatedBook() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        BookEntity savedBook = bookService.createBook(testBookA.getIsbn(),testBookA);

        BookDto testBook = TestDataUtil.createBook1(null);
        testBook.setIsbn(savedBook.getIsbn());
        String createBookJson= objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/"+ savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn" ).value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testBook.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(testBook.getAuthor())
        );
    };
    @Test
    public void testThatCreateBookReturnSavedABook() throws Exception {
        BookDto bookDto = TestDataUtil.createBook1(null);
        String createBookJson= objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/"+ bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn" ).value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }
    @Test
    public void thatGetListOfBooksReturnStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void thatGetListOfBooks() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        bookService.createBook(testBookA.getIsbn(),testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(" 1982-1-234-567")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("Jones is a stone")
        );
    }
    @Test
    public void testGetBookWithHttpStatus200() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        bookService.createBook(testBookA.getIsbn(),testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/"+testBookA.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnHttpStatus404IfNoAuthorExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/33")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatPartiallyUpdateBookReturnHttpStatus200() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        BookEntity savedBook = bookService.createBook(testBookA.getIsbn(),testBookA);

        BookDto testBook = TestDataUtil.createBook1(null);
        testBook.setTitle("Updated");
        String createBookJson= objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/books/"+ savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void testThatPartiallyUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        BookEntity savedBook = bookService.createBook(testBookA.getIsbn(),testBookA);

        BookDto testBook = TestDataUtil.createBook1(null);
        testBook.setTitle("Updated");
        String createBookJson= objectMapper.writeValueAsString(testBook);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/books/"+ savedBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(savedBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Updated")
        );
    }

    @Test
    public  void deleteBookWithHttpStatus204() throws Exception {
        BookEntity testBookA = TestDataUtil.createBook(null);
        BookEntity savedBook = bookService.createBook(testBookA.getIsbn(),testBookA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/"+ savedBook.getIsbn() )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

    }
}
