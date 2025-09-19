package com.example.springsecuritydemo2025.domain.response;

import com.example.springsecuritydemo2025.domain.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookResponse {

    private  Long id;
    private String message;

    private BookDto book;
}
