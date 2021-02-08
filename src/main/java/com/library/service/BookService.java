package com.library.service;

import com.library.dao.BookRepository;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.exception.BookAlreadyTakenException;
import com.library.exception.BookDoesNotBelongToUserException;
import com.library.exception.EntityNotFoundException;
import com.library.exception.ResourceIsAssignedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById (long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " is not found"));
    }

    public Book editBookById (long id, Book book) {
        Book bookFromDb = getBookById(id);
        bookFromDb.copyValuesFrom(book);
        return bookRepository.save(bookFromDb);
    }

    public void deleteBookById(long id) {
        Book book = getBookById(id);
        if (book.getUser() == null) {
            bookRepository.delete(book);
        } else {
            throw new ResourceIsAssignedException("Book with id " + id + " cannot be removed cause it was not returned by user");
        }
    }

    public void takeBook(long userId, long bookId) {
        User user = userService.getUserById(userId);
        Book book = getBookById(bookId);
        if (book.getUser() == null) {
            book.setUser(user);
            bookRepository.save(book);
        } else {
            throw new BookAlreadyTakenException();
        }
    }

    public void returnBook(long userId, long bookId) {
        User user = userService.getUserById(userId);
        Book book = getBookById(bookId);
        if (user.equals(book.getUser())) {
            book.setUser(null);
            bookRepository.save(book);
        } else {
            throw new BookDoesNotBelongToUserException();
        }
    }
}
