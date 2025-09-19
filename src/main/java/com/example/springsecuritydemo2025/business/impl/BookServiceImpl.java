package com.example.springsecuritydemo2025.business.impl;

import com.example.springsecuritydemo2025.business.Converter.BookConverterDto;
import com.example.springsecuritydemo2025.business.interfaces.BookService;
import com.example.springsecuritydemo2025.configuration.exceptions.BookAlreadyExistsException;
import com.example.springsecuritydemo2025.domain.dto.BookDto;
import com.example.springsecuritydemo2025.domain.request.CreateBookRequest;
import com.example.springsecuritydemo2025.domain.request.UpdateBookRequest;
import com.example.springsecuritydemo2025.domain.response.BookResponse;
import com.example.springsecuritydemo2025.persistence.entity.BookEntity;
import com.example.springsecuritydemo2025.persistence.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private  final BookRepo bookRepository;

    private  final BookConverterDto bookConverterDto;
    @Override
    public BookResponse createBook(CreateBookRequest request) {

        log.info("Creating new book: {}", request.getTitle());

        // Check if the contact already exists to avoid duplicates
        checkExistingBook(request);
        // Map the incoming request to a database entity
        BookEntity entity = bookConverterDto.mapToEntity(request);
        // Save the new contact entity in the database
        BookEntity savedEntity = bookRepository.save(entity);
        log.info("BOOK created successfully with ID: {}", savedEntity.getId());
        // Return a response with the ID and success message
        return BookResponse.builder()
                .id(savedEntity.getId())
                .book(bookConverterDto.mapToDTO(savedEntity))
                .message("Book created successfully")
                .build();

    }

    @Override
    public BookDto getBookById(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        return bookConverterDto.mapToDTO(book);
    }

    @Override
    public BookDto updateBook(UpdateBookRequest updateBookRequest) {
        BookEntity book = bookRepository.findById(updateBookRequest.getId())
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + updateBookRequest.getId()));

        // Update fields from the request
        book.setTitle(updateBookRequest.getTitle());
        book.setAuthor(updateBookRequest.getAuthor());
        book.setDescription(updateBookRequest.getDescription());
        book.setIsbn(updateBookRequest.getIsbn());
        book.setImage(updateBookRequest.getImage());

        // Save updated book
        BookEntity updatedBook = bookRepository.save(book);

        // Return updated DTO
        return bookConverterDto.mapToDTO(updatedBook);
    }


    @Override
    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);

    }

    @Override
    public List<BookDto> getAllBooks() {
        // Get all contacts from the database
        return bookRepository.findAll().stream().map(bookConverterDto::mapToDTO).toList();// ;
    }


    // Check if Book Object already exists
    private void checkExistingBook(CreateBookRequest request) {
        if (bookRepository.existsByTitle(request.getTitle())) {
            throw new BookAlreadyExistsException("Book already exists with name: " + request.getTitle());
        }

        if (bookRepository.existsByAuthor(request.getAuthor())) {
            throw new BookAlreadyExistsException("Book already exists with author: " + request.getAuthor());
        }

        if (bookRepository.existsByDescription(request.getDescription())) {
            throw new BookAlreadyExistsException("Book already exists with description: " + request.getDescription());
        }

    }

    }

