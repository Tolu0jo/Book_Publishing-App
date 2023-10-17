package com.example.publisher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//we are use lombok
@AllArgsConstructor //create getters and setter,hashCodes etc
@NoArgsConstructor //needed for jackson to create no args constructors and use getters and setters to set those field
@Builder
public class AuthorDto {
    private Long id;

    private String name;

    private Integer age;
}
