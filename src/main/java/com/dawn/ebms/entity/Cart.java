package com.dawn.ebms.entity;

import javax.persistence.*;

@Entity @IdClass(CartId.class)
public class Cart {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Id
    @Column(name = "book_id")
    private Integer bookId;

    private Integer quantity;

}