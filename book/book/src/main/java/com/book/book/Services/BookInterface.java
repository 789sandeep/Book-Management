package com.book.book.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.book.book.Model.Book;

public interface BookInterface {
    
    List<Book> getAllBook();
    ResponseEntity<?> getBookById(int id);
    Book saveBook(Book book);

    ResponseEntity<?> updateBookById(int id, Book book);
    ResponseEntity<?> deleteBookById(int id);
}
