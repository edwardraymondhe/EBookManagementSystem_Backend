package com.dawn.ebms.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BookColl {

    public BookColl(Integer bookId, String bookCover, String bookIntro) {
        this.bookId = bookId;
        this.bookCover = bookCover;
        this.bookIntro = bookIntro;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Id
    private ObjectId id;

    private Integer bookId;

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro;
    }

    private String bookCover;

    private String bookIntro;
}