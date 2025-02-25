package com.book.book.Services;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import com.book.book.Model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.book.Entity.BookEntity;

import com.book.book.Repositery.BookRepositery;

@Service
public class BookServiceImp implements BookInterface {
    BookRepositery bookRepositery;

    BookServiceImp(BookRepositery bookRepositery){
        this.bookRepositery=bookRepositery;
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> bookList=new ArrayList<>(); //ek list bnai jo book name ki jo khali h
        List<BookEntity> bookdb=bookRepositery.findAll(); // ye databse data lara h database ek list ke form
        for(BookEntity  bookEntity: bookdb){
            System.out.println(bookEntity);
        }
        for(BookEntity bookEntity:bookdb){
              Book book=new Book();
             BeanUtils.copyProperties(bookEntity, book);
             bookList.add(book);
        }
        return bookList;
    }
    @Override
    public ResponseEntity<?> getBookById(int id) {
        if(bookRepositery.findById(id).isEmpty()){
            return ResponseEntity.ok().body("Book is not present");
         }
        BookEntity bookEntity=bookRepositery.findById(id).get();
        Book book=new Book();
        BeanUtils.copyProperties(bookEntity, book);
        return ResponseEntity.ok().body(book);
    }

    @Override
    public Book saveBook(Book book) {
       BookEntity bookEntity= new BookEntity();
       BeanUtils.copyProperties(book,bookEntity);
       bookRepositery.save(bookEntity);
       return book;

    }

    @Override
    public ResponseEntity<?> updateBookById(int id, Book book) {
        if(bookRepositery.findById(id).isEmpty()){
            return ResponseEntity.ok().body("Book is not present");
         }
         BookEntity bookEntity=bookRepositery.findById(id).get();
         System.out.println(book.getBookName() !=null);
         System.out.println(!book.getBookName().isEmpty());
         if(book.getBookName() !=null && !book.getBookName().isEmpty()){
                 bookEntity.setBookName(book.getBookName());
         }
        //  System.out.println(book.getAuthorName());
        //  System.out.println(book.getAuthorName() != null);
        //  System.out.println(!book.getAuthorName().isEmpty());
        
         if(book.getAuthorName() != null && !book.getAuthorName().isEmpty()){
            bookEntity.setAuthorName(book.getAuthorName());
         }
         bookRepositery.save(bookEntity);
         return ResponseEntity.ok().body(bookEntity);
    }

    @Override
    public ResponseEntity<?> deleteBookById(int id) {
      if(bookRepositery.findById(id).isEmpty()){
        return ResponseEntity.ok().body("Book is not present");
     }
     BookEntity bookEntity=bookRepositery.findById(id).get();
      bookRepositery.delete(bookEntity);
      return ResponseEntity.ok().body("Delete successfully") ;
    }

    

    
    
}
