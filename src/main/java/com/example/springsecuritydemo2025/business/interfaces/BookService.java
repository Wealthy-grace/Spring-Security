package com.example.springsecuritydemo2025.business.interfaces;

import com.example.springsecuritydemo2025.domain.dto.BookDto;
import com.example.springsecuritydemo2025.domain.request.CreateBookRequest;
import com.example.springsecuritydemo2025.domain.request.UpdateBookRequest;
import com.example.springsecuritydemo2025.domain.response.BookResponse;

import java.util.List;

public interface BookService {


    BookResponse createBook(CreateBookRequest createBookRequest);

    BookDto getBookById(Long id);

    BookDto updateBook(UpdateBookRequest updateBookRequest);

    void deleteBook(Long id);


    // get all books
    List<BookDto> getAllBooks();


}
