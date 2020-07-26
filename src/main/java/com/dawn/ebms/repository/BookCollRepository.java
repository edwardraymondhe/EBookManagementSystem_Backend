package com.dawn.ebms.repository;

import com.dawn.ebms.entity.BookColl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookCollRepository extends MongoRepository<BookColl, Integer> {
    Optional<BookColl> findBookCollByBookIdEquals(Integer bookId);
    void deleteBookCollByBookIdEquals(Integer bookId);
}
