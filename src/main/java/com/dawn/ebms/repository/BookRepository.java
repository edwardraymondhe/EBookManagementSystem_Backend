package com.dawn.ebms.repository;

import com.dawn.ebms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByIsbnEquals(String isbn);
    Optional<Book> findBookByNameEquals(String name);
    Optional<Book> findBookByBookIdEquals(Integer bookId);
    List<Book> findBookByNameContains(String search);
}
