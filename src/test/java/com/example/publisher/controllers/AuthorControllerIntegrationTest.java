package com.example.publisher.controllers;


import com.example.publisher.TestDataUtil;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.services.AuthorService;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //cleanup each db after rwat
@AutoConfigureMockMvc //to create an instance of mock mvc
public class AuthorControllerIntegrationTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private AuthorService authorService;
    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService) {
        this.mockMvc = mockMvc;

        this.objectMapper = objectMapper;
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorsReturnStatusCreated() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorsReturnSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id" ).isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Jones stones")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(29)
        );
    }

    @Test
    public void thatGetListOfAuthorsReturnStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void thatGetListOfAuthors() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        authorService.createAuthor(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").isNumber()
        );
    }
}
