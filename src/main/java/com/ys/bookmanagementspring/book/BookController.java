package com.ys.bookmanagementspring.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @PostMapping("/addBook")
    public String addNewBook(Book book) {
        bookService.createBook(book);
        if (book == null)
            return "redirect:/";
        return "redirect:/";
    }

    @RequestMapping({"/add", "/edit/{id}"})
    public String editBook(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Optional<Book> book = bookService.findBookById(id.get());
            if (book.isPresent())
                model.addAttribute("book", book.get());
            else
                model.addAttribute("book", new Book());
        } else
            model.addAttribute("book", new Book());
        return "addOrEditBook";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }
}
