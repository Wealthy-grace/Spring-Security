package com.example.springsecuritydemo2025.controller;

import com.example.springsecuritydemo2025.business.interfaces.BookService;
import com.example.springsecuritydemo2025.configuration.exceptions.BookAlreadyExistsException;
import com.example.springsecuritydemo2025.domain.dto.BookDto;
import com.example.springsecuritydemo2025.domain.request.CreateBookRequest;
import com.example.springsecuritydemo2025.domain.request.UpdateBookRequest;
import com.example.springsecuritydemo2025.domain.response.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookRequest request, BindingResult bindingResult) {
        logger.info("Received create book request: {}", request);

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            logger.warn("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            BookResponse response = bookService.createBook(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            logger.warn("Book already exists: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            BookDto book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthController.ErrorResponse("Book not found", e.getMessage()));
        }
    }

    // PUT /api/books/update
    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody UpdateBookRequest request) {
        try {
            BookDto updatedBook = bookService.updateBook(request);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthController.ErrorResponse("Update failed", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthController.ErrorResponse("Delete failed", e.getMessage()));
        }
    }
}
