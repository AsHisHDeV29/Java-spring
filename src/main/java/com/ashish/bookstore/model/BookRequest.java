package com.ashish.bookstore.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BookRequest")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requestedBookName;

    @Column(nullable = false)
    private String status = "requested";

    @Column(nullable = false)
    private LocalDateTime requestDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public String getRequestedBookName() {
        return requestedBookName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequestedBookName(String requestedBookName) {
        this.requestedBookName = requestedBookName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public BookRequest() {}

}
