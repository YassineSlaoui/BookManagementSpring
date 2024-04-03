package com.ys.bookmanagementspring;

import com.ys.bookmanagementspring.book.Book;
import com.ys.bookmanagementspring.book.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookManagementSpringApplication implements ApplicationRunner {

    @Autowired
    BookServiceImpl bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookManagementSpringApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (bookService.findAllBooks().isEmpty())
            Arrays.asList(
                    Book.builder().name("A Book: One").description("First Book").price(5).build(),
                    Book.builder().name("A Book: Two").description("Second Book").price(10).build(),
                    Book.builder().name("A Book: Three").description("Third Book").price(15).build()
            ).forEach(book -> bookService.createBook(book));
    }
}
