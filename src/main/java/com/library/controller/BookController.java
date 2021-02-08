package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById (@PathVariable long id) {
        return new ResponseEntity<Book>(bookService.getBookById(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> createNewBook (@Valid @RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.addNewBook(book), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<Book> editBookById (@PathVariable long id, @RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.editBookById(id, book), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/users/{userId}/books/{bookId}")
    public ResponseEntity takeBook (@PathVariable long userId, @PathVariable long bookId) {
        bookService.takeBook(userId, bookId);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{userId}/books/{bookId}")
    public ResponseEntity returnBook (@PathVariable long userId, @PathVariable long bookId) {
        bookService.returnBook(userId, bookId);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
}
