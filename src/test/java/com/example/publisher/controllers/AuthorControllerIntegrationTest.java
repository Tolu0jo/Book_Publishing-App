package com.example.publisher.controllers;


import com.example.publisher.TestDataUtil;
import com.example.publisher.entitities.AuthorEntity;
import com.example.publisher.services.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        authorService.save(testAuthorA);
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
    @Test
    public void testGetAuthorWithHttpStatus200() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        authorService.save(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/authors/"+testAuthorA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnHttpStatus404IfNoAuthorExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/authors/33")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatUpdateAuthorsReturnSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);

        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/authors/"+savedAuthor .getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void testThatUpdateAuthorsReturn404Author() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor();
        String authorJson = objectMapper.writeValueAsString(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/authors/99" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testFullUpload() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor1();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createAuthor2();
        testAuthorB.setId(savedAuthor.getId());
        String authorJson = objectMapper.writeValueAsString(testAuthorB);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/authors/"+ savedAuthor.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testAuthorB.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorB.getAge())
        );

    }
    @Test
    public void testPartialUploadReturnsStatus200() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor1();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createAuthor2();
        testAuthorB.setName("Updated");
        String authorJson = objectMapper.writeValueAsString(testAuthorB);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/authors/"+ savedAuthor.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
       );

    }
    @Test
    public void testPartialUploadReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor1();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);
        AuthorEntity testAuthorB = TestDataUtil.createAuthor2();
        testAuthorB.setName("Updated");
        String authorJson = objectMapper.writeValueAsString(testAuthorB);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/authors/"+ savedAuthor.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Updated")
        );

    }

    @Test
    public  void deleteAuthorWithHttpStatus204() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createAuthor1();
        AuthorEntity savedAuthor = authorService.save(testAuthorA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/authors/"+ savedAuthor.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

    }
}
