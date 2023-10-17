package com.example.publisher.mappers;

import com.example.publisher.domain.dto.AuthorDto;
import com.example.publisher.entitities.AuthorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorEntity, AuthorDto> {

     private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
       return modelMapper.map(authorEntity,AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto,AuthorEntity.class);
    }
}
