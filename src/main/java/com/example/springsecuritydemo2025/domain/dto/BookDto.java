package com.example.springsecuritydemo2025.domain.dto;


import com.example.springsecuritydemo2025.persistence.entity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private String description;

    private String isbn;


    private String category;

    private String image;
}
