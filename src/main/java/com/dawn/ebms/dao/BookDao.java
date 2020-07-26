package com.dawn.ebms.dao;

import com.dawn.ebms.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookDao {
    List<Book> getBooks();

    Book findBookByBookId(int id);

    Optional<Book> findBookByIsbn(String isbn);

    Optional<Book> findBookByName(String name);

    List<Integer> findBookByNames(String books);

    Book addBookByIsbn(String isbn);

    Book addBookByName(String name);

    List<Book> findBookBySearch(String search);

    void deleteBookByBookId(Integer id);

    Integer updateBook(Integer type, Integer id, String value);

    Integer setBookIsbnByBookId(Integer id, String isbn);

    Integer setBookNameByBookId(Integer id, String name);

    Integer setBookCategoryByBookId(Integer id, String category);

    Integer setBookAuthorByBookId(Integer id, String author);

    Integer setBookPriceByBookId(Integer id, Double price);

    Integer setBookLanguageByBookId(Integer id, String language);

    Integer setBookDateByBookId(Integer id, String date);

    Integer setBookSalesByBookId(Integer id, String sales);

    Integer setBookStatusByBookId(Integer id, String status);

    Integer setBookStockByBookId(Integer id, Integer stock);

    Integer setBookCoverByBookId(Integer id, String cover);

    Integer setBookIntroByBookId(Integer id, String cover);

}
