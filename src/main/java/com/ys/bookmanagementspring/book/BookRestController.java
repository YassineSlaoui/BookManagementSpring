package com.ys.bookmanagementspring.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return bookService.findBookById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addNewBook(@RequestBody Book book) {
        Book bookToInsert = bookService.createBook(book);
        if (bookToInsert == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(bookToInsert.getId(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
