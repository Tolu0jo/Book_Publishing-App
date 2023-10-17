package com.example.publisher.mappers;

import com.example.publisher.domain.dto.BookDto;
import com.example.publisher.entitities.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {
    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity,BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {

        return modelMapper.map(bookDto,BookEntity.class);
    }
}
