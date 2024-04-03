package com.ys.bookmanagementspring.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepo.save(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return bookRepo.findById(id);
    }
}
