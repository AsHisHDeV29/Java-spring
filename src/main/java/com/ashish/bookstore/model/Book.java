package com.ashish.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private boolean inStock;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int quantityLeft;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public Book() {}

    public boolean isInStock() {
        return quantityLeft > 0;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

}
