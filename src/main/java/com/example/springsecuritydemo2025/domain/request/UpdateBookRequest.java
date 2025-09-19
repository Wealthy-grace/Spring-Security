package com.example.springsecuritydemo2025.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateBookRequest {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private String description;

    private String image;
}
