package com.ys.bookmanagementspring.book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> findAllBooks();

    Book createBook(Book book);

    void updateBook(Book book);

    void deleteBook(long id);

    Optional<Book> findBookById(long id);
}
