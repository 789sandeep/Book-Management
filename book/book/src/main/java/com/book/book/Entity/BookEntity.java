package com.book.book.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookEntity {
     @Id
     private int id;
     private String bookName;
     private String authorName;

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getBookName() {
          return bookName;
     }

     public void setBookName(String bookName) {
          this.bookName = bookName;
     }

     public String getAuthorName() {
          return authorName;
     }

     public void setAuthorName(String authorName) {
          this.authorName = authorName;
     }
}
