package com.book.book.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.book.book.Model.Book;
import com.book.book.Services.BookServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BookController {

    @Autowired
    BookServiceImp bookServiceImp;

    BookController(BookServiceImp bookServiceImp){
        this.bookServiceImp=bookServiceImp;
    }
    

    @RequestMapping("/frm")
    public String bookData() {
        return "Bookform";
    }

    @GetMapping("/bookList")
    public String getMethodName(Model model) {
        List<Book> books = bookServiceImp.getAllBook();
        model.addAttribute("books", books);
        return "Showbook";
    }

    @GetMapping("/bookbyid")
    public String getMethodName(@RequestParam int id ,Model model) {
        ResponseEntity<?> result=bookServiceImp.getBookById(id);
        if (result.getBody() instanceof String) {
            model.addAttribute("Message",result.getBody());
        }
        else{
            model.addAttribute("books",result.getBody());
        }
        
        return "Bookbyid";
    }
    

    @PostMapping("/bookForm")
    public String postMethodName(Book book, Model model) {
        model.addAttribute("bookDetails", book);
        bookServiceImp.saveBook(book);
        model.addAttribute("successMessage", "Book add successfully!");
        // return ResponseEntity.ok().body("Book save successfully");
        return "Bookform";
    }

    @PutMapping
    @RequestMapping("/updateBook")
    public String updateMethod(@RequestParam int id, @ModelAttribute Book book, Model model) {
        ResponseEntity<?> response = bookServiceImp.updateBookById(id, book);
        System.out.println(response);
        // model.addAttribute("updateBook",book);

        if (response.getBody() instanceof String) {
            model.addAttribute("Message", response.getBody());

        } else {
            model.addAttribute("updateBook",book);
            model.addAttribute("successMessage", "Book updated successfully!");
        } 
        return "Bookform";

    }

    @DeleteMapping
    @RequestMapping("/deleteBook")
    public String deleteMethod(@RequestParam int id, Model model) {
        ResponseEntity<?> response= bookServiceImp.deleteBookById(id);
        model.addAttribute("Message",response.getBody());
        return "Bookform";
    }

}
