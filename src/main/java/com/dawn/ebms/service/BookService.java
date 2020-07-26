package com.dawn.ebms.service;

import com.dawn.ebms.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> getBooks();
    Book findBookByBookId(int id);
    List<Book> findBookBySearch(String search);
    List<Integer> findBookByNames(String books);
    Book addBookByIsbn(String isbn);

    Book addBookByName(String name);

    void deleteBookByBookId(Integer id);

    Integer updateBook(Integer type, Integer id, String value);

}
