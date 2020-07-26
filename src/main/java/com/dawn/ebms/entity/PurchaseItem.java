package com.dawn.ebms.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity @IdClass(PurchaseItemId.class)
public class PurchaseItem {

    @Id
//    @JoinColumn(name = "order_id")
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Id
//    @JoinColumn(name = "book_id")
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "price")
    private double price;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity")
    private Integer quantity;
}


