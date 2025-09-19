package com.example.springsecuritydemo2025.business.Converter;

import com.example.springsecuritydemo2025.domain.dto.BookDto;
import com.example.springsecuritydemo2025.domain.request.CreateBookRequest;
import com.example.springsecuritydemo2025.persistence.entity.BookEntity;
import com.example.springsecuritydemo2025.persistence.entity.Category;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BookConverterDto {

    /**
     * Converts a BookEntity to a BookDto.
     */
    public BookDto mapToDTO(BookEntity entity) {
        return BookDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .category(entity.getCategory().name())
                .isbn(entity.getIsbn())
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();

    }

    /**
     * Converts a CreateBookRequest to a BookEntity.
     */
    public BookEntity mapToEntity(CreateBookRequest request) {
        return BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .category(request.getCategory()) // ⭐ this is already an enum
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
    }
}
