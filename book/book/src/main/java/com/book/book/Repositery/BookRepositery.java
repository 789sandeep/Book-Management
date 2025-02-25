package com.book.book.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.book.Entity.BookEntity;

@Repository
public interface BookRepositery extends JpaRepository<BookEntity,Integer> {
    
}
